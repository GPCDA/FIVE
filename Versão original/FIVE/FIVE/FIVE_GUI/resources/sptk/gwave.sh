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
#                1996-2008  Nagoya Institute of Technology          #
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
#   Draw a Waveform                                                     #
#                                                                       #
#                                               1997.1  K.Koishida      #
#                                                                       #
#########################################################################

onintr clean

set path    = ( /home/carlos/Programs/SPTK-3.3/bin $path )
set sptkver = '3.3'
set cvsid   = '$Id: gwave.in,v 1.10 2008/06/16 05:47:55 heigazen Exp $'

set cmnd = $0
set cmnd = $cmnd:t
set file

@ start     = 0
@ term      = -1
@ numscreen = 5
@ numdata   = 2000
set ymax    = 2000
set pen     = 1
set type    = f

set flagy2      = 0
set flagfile    = 0
set flagnumdata = 0
set flagy       = 0

@ i = 0
while ($i < $#argv)
        @ i++
        switch ($argv[$i])
        case -s:
                @ i++
                set start = $argv[$i]
                breaksw
        case -e:
                @ i++
                set term = $argv[$i]
                breaksw
        case -n:
                @ i++
                @ numdata = $argv[$i]
                set flagnumdata = 1
                breaksw
        case -i:
                @ i++
                set numscreen = $argv[$i]
                breaksw
        case -y:
                @ i++
                set ymax = $argv[$i]
                set flagy = 1
                breaksw
        case -y2:
                @ i++
                set ymin = $argv[$i]
                set flagy = 1
                set flagy2 = 1
                breaksw
        case -p:
                @ i++
                set pen = $argv[$i]
                breaksw
        case +s:
                set type = s
                breaksw
        case +i:
                set type = i
                breaksw
        case +f:
                set type = f
                breaksw
        case +d:
                set type = d
                breaksw
        case -h:
                set exit_status = 0
                goto usage
                breaksw
        default:
                set file = $argv[$i]
                set flagfile = 1
                if ( ! -f $file ) then
                        echo2 "${cmnd} : Cannot open file ${file}\!"
                        set exit_status = 1
                        goto usage
                endif
                breaksw
        endsw
end

cat $file > /tmp/$$
set file = /tmp/$$

goto main

usage:
        echo2 ''
        echo2 " $cmnd - draw a waveform"
        echo2 ''
        echo2 '  usage:'
        echo2 "       $cmnd [ options ] [ infile ] > stdout"
        echo2 '  options:'
        echo2 '       -s  s         : start point                  [0]'
        echo2 '       -e  e         : end point                    [EOF]'
        echo2 '       -n  n         : data number of one screen    [N/A]'
        echo2 '       -i  i         : number of screen             [5]'
        echo2 '       -y  ymax      : maximum amplitude            [N/A]'
        echo2 '       -y2 ymin      : minimum amplitude            [-ymax]'
        echo2 '       -p  p         : pen number                   [1]'
        echo2 '       +type         : input data type              [f]'
        echo2 '                        s (short)  i (int)             '
        echo2 '                        f (float)  d (double)          '
        echo2 '       -h            : print this message'
        echo2 '  infile:'
        echo2 '       waveform                                     [stdin]'
        echo2 '  stdout:'
        echo2 '       XY-plotter command'
        echo2 '  notice:'
        echo2 '       If options of amplitude are not used,'
        echo2 '        value of amplitude is automatically determined.'
        echo2 '       If -n option is not used,'
        echo2 '        entire waveform is displayed.'
        echo2 ''
        echo2 " SPTK: version $sptkver"
        echo2 " CVS Info: $cvsid"
        echo2 ''
exit $exit_status

main:

if ( ! $flagnumdata ) then
        if ( $term > 0 ) then
                @ wc = $term
        else
                set wc = ` x2x +${type}a $file | wc -l `
        endif
        @ wc = $wc - $start
                
        @ rest = $wc % $numscreen
        @ wc /= $numscreen
        if ($rest) @ wc++
        @ numdata = $wc
endif

if ( ! $flagy ) then
        set val = (`x2x +${type}f < $file | minmax | x2x +fs | x2x +sa`)

        if ( $val[1] < 0 ) then
                @ val[1] *= -1
        endif

        if ( $val[1] > $val[2] ) then
                @ ymax = $val[1]
                @ ymin = -$val[1]
        else
                @ ymax = $val[2]
                @ ymin = -$val[2]
        endif
endif

if ( ! $flagy2 ) then
        set ymin = -$ymax
endif

set width  = 1.7
set height = 0.4

@ n1  = $numdata - 1
@ end = $start + $n1
@ ox  = 20
@ oy  = 225
@ xs  = $start
@ xe  = $xs + $n1

if ( $numdata < 10 ) then
        @ dx = 1
else
        @ dx = $numdata / 10
endif

while ( $numscreen > 0 )

        @ i = 0
        set xscale = ()
        while ( $i < 10 )
                @ x = $dx * $i + $xs
                set xscale = ($xscale $x)
                @ i++
        end

        fig -o $ox $oy -W $width -H $height -p $pen << EOF
                x      $xs $xe
                y      $ymin $ymax
                xscale $xscale
                yscale $ymin $ymax
EOF

        x2x +${type}f $file |\
        bcut +f -s $start -e $end |\
        fdrw -o $ox $oy -W $width -H $height -p $pen -g 0 \
             -y $ymin $ymax -n $numdata

        @ numscreen--
        @ oy    -= 50
        @ xs    += $numdata
        @ xe    += $numdata
        @ start += $numdata
        @ end   += $numdata

end

clean:
/bin/rm -f /tmp/$$

