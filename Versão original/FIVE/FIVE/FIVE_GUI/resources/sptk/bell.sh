#!csh -f
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
#   Bell                                                                #
#                                                                       #
#                                               1996.6  K.Koishida      #
#                                                                       #
#########################################################################

set path    = ( /home/carlos/Programs/SPTK-3.3/bin $path )
set sptkver = '3.3'
set cvsid   = '$Id: bell.in,v 1.5 2009/12/16 13:12:39 uratec Exp $'

set cmnd    = $0
set cmnd    = $cmnd:t

set no      = 1

@ i = 0
while ($i < $#argv)
        @ i++
        switch ($argv[$i])
        case -h:
                set exit_status = 0
                goto usage
                breaksw
        default:
                @ no = $argv[$i]
                breaksw
        endsw
end
goto main

usage:
        echo2 ''
        echo2 " $cmnd - bell"
        echo2 ''
        echo2 '  usage:'
        echo2 "       $cmnd [ option ] [ num ] > stdout"
        echo2 '  option:'
        echo2 '       -h  : print this message'
        echo2 '  notice:'
        echo2 '       num : number of bell      [1]'
        echo2 ''
        echo2 " SPTK: version $sptkver"
        echo2 " CVS Info: $cvsid"
exit $exit_status

main:
if ( $no < 1 ) set no = 1

set i = 1
while ( $i < $no )
        echo2 -n ''
        @ i++
        sleep 1
end
echo2 -n ''

