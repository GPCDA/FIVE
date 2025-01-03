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
#   play 16-bit linear PCM                                              #
#                                                                       #
#                                               2000.4  S.Sako          #
#                                                                       #
#########################################################################

set path        = ( /home/carlos/Programs/SPTK-3.3/bin $path )
set libpath     = '/home/carlos/Programs/SPTK-3.3/lib'
set sptkver     = '3.3'
set cvsid       = '$Id: da.in,v 1.8 2009/12/16 13:12:27 uratec Exp $'

set cmnd        = $0
set cmnd        = $cmnd:t
set file
set stdinput    = 1
set arch        = `uname -m`

set freq        = 10
set ampl        = 50
set gain        = 0
set ftype       = s
set port        = s
set headersize  = 0
set verbosemode = 0

set byteswap    = -1
set daoption    = ""

@ i = 0
while ($i < $#argv)
        @ i++
        switch ($argv[$i])
        case -s:
                @ i++
                set freq = $argv[$i]
                breaksw
        case -o:
                @ i++
                set port = $argv[$i]
                set daoption = ( $daoption -o $port )
                   breaksw
        case -a:
                @ i++
                set ampl = $argv[$i]
                set daoption = ( $daoption -a $ampl )
                breaksw
        case -g:
                @ i++
                set gain = $argv[$i]
                set daoption = ( $daoption -a $gain )
                breaksw
        case -H:
                @ i++
                set headersize = $argv[$i]
                set daoption = ( $daoption -H $headersize )
                breaksw
        case -v:
                set verbosemode = 1
                breaksw
        case -w:
                set byteswap = 1
                breaksw
        case +s:
                set ftype = s
                breaksw
        case +f:
                set ftype = f
                breaksw
        case -h:
                set exit_status = 0
                goto usage
        default
                set file = ( $file $argv[$i] )
                set stdinput = -1
                breaksw
        endsw
end
   
if ( $ftype == "f" ) then
        if ( $byteswap > 0 ) then
                alias fileconvert 'swab +f \!^'
        else
                alias fileconvert 'cat \!^'
        endif
else
        if( $byteswap > 0 ) then
                alias fileconvert 'swab +s \!^ | x2x +sf'
        else
                alias fileconvert 'x2x +sf \!^'
        endif
endif

switch ($arch)
        case sun4*:
                goto cnvt48
                breaksw
        case i?86:
        case x86_64:
                goto cnvt44
                breaksw
        endsw
exit 0


usage:

cat2 <<EOF

 da - play 16-bit linear PCM data

  usage:
       da [ options ] [ infile1 ] [ infile2 ] ...
EOF

switch($arch)
        case sun4*:
                cat2 <<EOF
  options:
       -s s  : sampling frequency ( 8, 10, 11.025, 12, 16, 20, 22,
                                       22.05, 32, 44.1, 48 kHz)   [10]
       -g g  : gain (..,-2,-1,0,1,2,...)                          [0]
       -a a  : amplitude gain (0..100)                            [N/A]
       -o o  : output port                                        [s]
                  s (speaker)   h (headphone)
       -H H  : header size in byte                                [0]
       -v    : display filename                                   [FALSE]
       -w    : execute byte swap                                  [FALSE]
       +x    : data format                                        [s]
                  s (short)   f (float)
       -h    : print this message
  infile:
       data sequence                                              [stdin]
  notice:

  SPTK: version $sptkver
  CVS Info: $cvsid
  
EOF
                exit 1

        case i?86:
        case x86_64:
                cat2 <<EOF
  options:
       -s s  : sampling frequency ( 8, 10, 11.025, 12, 16, 20,
                                              22.025, 44.1 kHz)  [10]
       -g g  : gain (..,-2,-1,0,1,2,...)                         [0]
       -a a  : amplitude gain (0..100)                           [N/A]
       -H H  : header size in byte                               [0]
       -v    : display filename                                  [FALSE]
       -w    : execute byte swap                                 [FALSE]
       +x    : data format                                       [s]
                  s (short)   f (float)
       -h    : print this message
  infile:
       data sequence                                             [stdin]
  notice:

 SPTK: version $sptkver
 CVS Info: $cvsid
  
EOF
                exit 1
endsw

endif


cnvt48:

if ( $stdinput == 1 ) then
        set infile = ""
        goto stdin48
endif

foreach infile ( ${file} )
        if ( ! -f $infile ) then
                echo2 "${cmnd} : Cannot open file ${infile}\!"
                break
        endif
        if ( $verbosemode ) then
                echo2 "${cmnd} : ${infile}"
        endif

        stdin48:
        switch ($freq)
                case 8:
                        fileconvert $infile |\
                        dawrite $daoption[*] +f -s 8
                        breaksw
                case 10:
                        fileconvert $infile |\
                        us -c ${libpath}/lpfcoef.5to8 -d 5 -u 8 |\
                        dawrite $daoption[*] +f -s 16
                        breaksw
                case 11:
                case 11.025:
                        fileconvert $infile |\
                        dawrite $daoption[*] +f -s 11.025
                        breaksw
                case 12:
                        fileconvert $infile |\
                        us -c ${libpath}/lpfcoef.3to4 -d 3 -u 4 |\
                        dawrite $daoption[*] +f -s 16
                        breaksw
                case 16:
                        fileconvert $infile |\
                        dawrite $daoption[*] +f -s 16
                        breaksw
                case 20:
                        fileconvert $infile |\
                        ds -s 54 |\
                        dawrite $daoption[*] +f -s 16
                        breaksw
                case 22:
                case 22.05:
                        fileconvert $infile |\
                        dawrite $daoption[*] +f -s 22.05
                        breaksw
                case 32:
                        fileconvert $infile |\
                        dawrite $daoption[*] +f -s 32
                        breaksw
                case 44:
                case 44.1:
                        fileconvert $infile |\
                        dawrite $daoption[*] +f -s 44.1
                        breaksw
                case 48:
                        fileconvert $infile |\
                        dawrite $daoption[*] +f -s 48
                        breaksw
                default
                        echo2 "${cmnd} : Unavailable sampling frequency ${freq}!"
                        goto usage
        endsw
        if( $stdinput == 1 ) then
                exit 0
        endif            
end
exit 0

cnvt44:

switch ($freq)
        case 8:
                @ osr = 4
                breaksw
        case 10:
                @ osr = 5
                breaksw
        case 12:
                @ osr = 6
                breaksw
        case 16:
                @ osr = 8
                breaksw
        case 20:
                @ osr = 10
                breaksw
        case 11:
        case 11.025:
        case 22:
        case 22.05:
        case 44:
        case 44.1:
                @ osr = -1
                breaksw
        default
                goto usage
endsw

@ osr *= 2

if( $stdinput == 1 ) then
        set infile = ""
        goto stdin44
endif

foreach infile ($file)
        if ( ! -f $infile ) then
                echo2 "${cmnd} : Cannot open file ${file}\!"
                break
        endif
        if ( $verbosemode ) then
                echo2 "${cmnd} : ${infile}"
        endif

        stdin44:
        if( $osr > 0 ) then
                fileconvert $infile |\
                us -c ${libpath}/lpfcoef.2to3f -u3 -d2 |\
                us -c ${libpath}/lpfcoef.2to3s -u3 -d2 |\
                us -c ${libpath}/lpfcoef.5to7 -u7 -d5 |\
                us -c ${libpath}/lpfcoef.5to7 -u7 -d$osr |\
                dawrite $daoption[*] +f -s 22.05
        else
                fileconvert $infile |\
                dawrite $daoption[*] +f -s $freq
        endif        
        if( $stdinput == 1 ) then
                exit 0
        endif            
end
exit 0

