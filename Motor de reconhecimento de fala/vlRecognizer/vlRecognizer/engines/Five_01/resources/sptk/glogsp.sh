#! csh -f
# ----------------------------------------------------------------- #
#             The Speech Signal Processing Toolkit (SPTK)           #
#             developed by SPTK Working Group                       #
#             http://sp-tk.sourceforge.net/                         #
# ----------------------------------------------------------------- #
#                                                                   #
#  Copyright (c) 1984-2007  Tokyo Institute of Technology           #
#                           Interdisciplinary Graduate School of    #
#                           Science and Engineering                 #
#                                                                   #
#                1996-2009  Nagoya Institute of Technology          #
#                           Department of Computer Science          #
#                                                                   #
# All rights reserved.                                              #
#                                                                   #
# Redistribution and use in source and binary forms, with or        #
# without modification, are permitted provided that the following   #
# conditions are met:                                               #
#                                                                   #
# - Redistributions of source code must retain the above copyright  #
#   notice, this list of conditions and the following disclaimer.   #
# - Redistributions in binary form must reproduce the above         #
#   copyright notice, this list of conditions and the following     #
#   disclaimer in the documentation and/or other materials provided #
#   with the distribution.                                          #
# - Neither the name of the SPTK working group nor the names of its #
#   contributors may be used to endorse or promote products derived #
#   from this software without specific prior written permission.   #
#                                                                   #
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND            #
# CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,       #
# INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF          #
# MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE          #
# DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS #
# BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,          #
# EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED   #
# TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,     #
# DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON #
# ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,   #
# OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY    #
# OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE           #
# POSSIBILITY OF SUCH DAMAGE.                                       #
# ----------------------------------------------------------------- #


#########################################################################
#                                                                       #
#   Draw a Log Spectrum Graph                                           #
#                                                                       #
#                                               1988.5  K.Tokuda        #
#                                               1996.6  K.Koishida      #
#                                                                       #
#########################################################################

set path    = ( /home/carlos/Programs/SPTK-3.3/bin $path )
set sptkver = '3.3'
set cvsid   = '$Id: glogsp.in,v 1.11 2009/12/16 13:12:39 uratec Exp $'

set cmnd = $0
set cmnd = $cmnd:t

set h    = 0.6
set w    = 0.6
@ on     = 1
@ x      = 1
@ ymin   = 0
@ ymax   = 100
@ yscale = 20
set yy   = ("" "")
set p    = 1
set ln   = 1
@ g      = 2
set file

@ s = 0
@ l = 256

set help = 0

set xname = (   "Normalized frequency" \
                "Normalized frequency (rad)" "" \
                "Frequency (kHz)" \
                "Frequency (kHz)" "" "" \
                "Frequency (kHz)" "" \
                "Frequency (kHz)" )
set xs = (      "0 0.25 0.5" \
                0\ \'1.57\ \"\\pi/2\"\ \'3.14\ \"\\pi\" ""\
                "0 1 2 3 4" \
                "0 1 2 3 4 5" "" ""\
                "0 2 4 6 8" "" \
                "0 2 4 6 8 10" )
set xx = ("0 0.5" "0 3.14" "" "0 4" "0 5" "" "" "0 8" "" "0 10")
set xl = (0.5 3.14 "" 4 5 "" "" 8 "" 10)

set on_x = (40 125 40 125 40 125)
set on_y = (205 205 120 120 35 35)

@ i = 0
while ($i < $#argv)
        @ i++
        switch ($argv[$i])
        case -f:
                @ i++
                set f = $argv[$i]
                breaksw
        case -v:
                set v = over
                breaksw
        case -H:
                @ i++
                set h = $argv[$i]
                breaksw
        case -W:
                @ i++
                set w = $argv[$i]
                breaksw
        case -o:
                @ i++
                @ xo = $argv[$i]
                @ i++
                @ yo = $argv[$i]
                breaksw
        case -O:
                @ i++
                @ on = $argv[$i]
                breaksw
        case -x:
                @ i++
                @ x = $argv[$i]
                breaksw
        case -y:
                @ i++
                @ ymin = $argv[$i]
                @ i++
                @ ymax = $argv[$i]
                breaksw
        case -ys:
                @ i++
                @ yscale = $argv[$i]
                breaksw
        case -p:
                @ i++
                set p = $argv[$i]
                breaksw
        case -ln:
                @ i++
                set ln = $argv[$i]
                breaksw
        case -c:
                @ i++
                set c = "$argv[$i]"
                breaksw
        case -g:
                @ i++
                @ g = $argv[$i]
                breaksw
        case -s:
                @ i++
                set s = $argv[$i]
                breaksw
        case -l:
                @ i++
                @ l = $argv[$i]
                breaksw
        case -help:
                set help = 1
        case -h:
                set exit_status = 0
                goto usage
                breaksw
        default:
                set file = $argv[$i]
                if ( ! -f $file ) then
                        echo2 "${cmnd} : Cannot open file ${file}\!"
                        set exit_status = 1
                        goto usage
                endif
                breaksw
        endsw
end
goto main

usage:
        echo2 ''
        echo2 " $cmnd - draw a log spectrum graph"
        echo2 ''
        echo2 '  usage:'
        echo2 "       $cmnd [ options ] [ infile ] > stdout"
        echo2 '  options:'
        echo2 '       -O  O         : origin of graph              [1]    '
if ( $help ) then
        echo2 '                        1 ( 40, 205) <mm> '
        echo2 '                        2 (125, 205) <mm>   +--------+'
        echo2 '                        3 ( 40, 120) <mm>   | 1    2 |'
        echo2 '                        4 (125, 120) <mm>   | 3    4 |'
        echo2 '                        5 ( 40,  35) <mm>   | 5    6 |'
        echo2 '                        6 (125,  35) <mm>   +--------+'
endif
        echo2 '       -x  x         : x scale                      [1]'
if ( $help ) then
        echo2 '                        1  (Normalized frequency <0, 0.5>)'
        echo2 '                        2  (Normalized frequency <0, pi/2, pi>)'
        echo2 '                        4  (Frequency <0, 1, 2, 3, 4     kHz>)'
        echo2 '                        5  (Frequency <0, 1, 2, 3, 4, 5  kHz>)'
        echo2 '                        8  (Frequency <0, 2, 4, 6, 8     kHz>)'
        echo2 '                        10 (Frequency <0, 2, 4, 6, 8, 10 kHz>)'
endif
        echo2 '       -y  ymin ymax : y scale                      [0 100]'
        echo2 '       -ys ys        : Y-axis scaling factor        [20]'
        echo2 '       -p  p         : pen no.                      [1]'
        echo2 '       -ln ln        : line number                  [1]'
        echo2 '       -s  s         : start frame number           [0]'
        echo2 '       -l  l         : frame length                 [256]'
        echo2 '       -c  "c"       : comment for the graph        [""]'
        echo2 '       -h            : print this message'
        echo2 '       -help         : print help in detail'
if ( $help ) then
        echo2 '       (level 2)'
        echo2 '       -W  W         : width of the graph <100mm>   [0.6]'
        echo2 '       -H  H         : height of the graph <100mm>  [0.6]'
        echo2 '       -v            : over write mode              [FALSE]'
        echo2 '       -o  xo yo     : origin of the graph          [40, 205]'
        echo2 '                      (if -o option exists, -O is not effective)'
        echo2 '       -g  g         : type of the graph            [2]'
        echo2 '                        0 (no frame)'
        echo2 '                        1 (half frame)'
        echo2 '                        2 (with frame)'
        echo2 '       -f  file      : additional data file for fig [NULL]'
endif
        echo2 '  infile:'
        echo2 "       log spectrum (float)                         [stdin]"
        echo2 '  stdout:'
        echo2 '       XY-plotter command'
        echo2 ''
        echo2 " SPTK: version $sptkver"
        echo2 " CVS Info: $cvsid"
        echo2 ''
exit $exit_status

main:
if (! $?xo) then
        @ xo = $on_x[$on]
        @ yo = $on_y[$on]
endif

@ yy[1] = $ymin
@ yy[2] = $ymax

set ys = ($ymin)

@ i = 1
while (($ys[$i] + $yscale) <= $ymax)
  @ tmp = $ys[$i] + $yscale
  set ys = ($ys $tmp)
  @ i++
end

if ($?c) then
        # echo "$c"
        @ yoc = $yo - 8
        fig -W $w -H $h -o $xo $yoc -g 0 << EOF
        xname "$c"
EOF
endif

fig -W $w -H $h -o $xo $yo -g $g << EOF
        x $xx[$x]
        y $yy
        xscale $xs[$x]
        xname "$xname[$x]"
        yscale $ys
        yname "Log magnitude (dB)"

        line 2
        0 0
        $xl[$x] 0
        eod

EOF

if ($?f) then
        fig $f -W $w -H $h -o $xo $yo -g 0
endif

@ lhi = $l / 2 + 1
@ s = $s * $lhi
if ($?v) then
        set ee = ""
else
        @ e = $s + $lhi - 1
        set ee = "-e $e"
endif

bcut +f -s $s $ee $file |\
fdrw -W $w -H $h -o $xo $yo -g 0 -y $yy -n $lhi -p $p -m $ln

