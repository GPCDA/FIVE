package br.ufpe.cin.five.core.extraction.mgcc;

import br.ufpe.cin.five.core.extraction.ExtractionException;
import br.ufpe.cin.five.core.project.ProjectUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author carlos
 */
public class GenerateTrainingFiles {

    private MgccParameters mgcParameters;
    private String projectDir;
    private File scriptsTrainingDir;
    private File questionsDir;
    private File winDir;
    private String resourcesPath;
    private BufferedWriter bw;

    public GenerateTrainingFiles(String projectDir, String resourcesPath, String extractionPath, MgccParameters mgcParameters) {
        try {
            this.scriptsTrainingDir = new File(projectDir + File.separator + "training" + File.separator + "scripts");
            this.questionsDir = new File(extractionPath + File.separator + "questions");
            this.winDir = new File(extractionPath + File.separator + "win");
            ProjectUtil.checkExists(scriptsTrainingDir, questionsDir, winDir);
            this.mgcParameters = mgcParameters;
            this.resourcesPath = resourcesPath;
            this.projectDir = projectDir;
            bw = new BufferedWriter(new FileWriter(scriptsTrainingDir + File.separator + "Config.pm"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void generateFiles()throws ExtractionException {
        try {
            generateConfigFile();
            FileUtils.copyFileToDirectory(new File(resourcesPath + File.separator + "scripts" + File.separator + "training" + File.separator + "scripts" + File.separator + "Training.pl"), scriptsTrainingDir);
            FileUtils.copyDirectory(new File(resourcesPath + File.separator + "scripts" + File.separator + "training" + File.separator + "data" + File.separator + "questions"), questionsDir);
            FileUtils.copyDirectory(new File(resourcesPath + File.separator + "scripts" + File.separator + "training" + File.separator + "data" + File.separator + "win"), winDir);
        } catch (Exception ex) {
            throw new ExtractionException(ex.getMessage());
        }
    }

    private void generateConfigFile() throws IOException {
        bw.write("#!/usr/bin/perl \n");
        bw.write("# ----------------------------------------------------------------- \n");
        bw.write("#           The HMM-Based Speech Synthesis System (HTS)             \n");
        bw.write("#           developed by HTS Working Group                          \n");
        bw.write("#           http://hts.sp.nitech.ac.jp/                             \n");
        bw.write("# ----------------------------------------------------------------- \n");
        bw.write("#                                                                   \n");
        bw.write("#  Copyright (c) 2001-2008  Nagoya Institute of Technology          \n");
        bw.write("#                           Department of Computer Science          \n");
        bw.write("#                                                                   \n");
        bw.write("#                2001-2008  Tokyo Institute of Technology           \n");
        bw.write("#                           Interdisciplinary Graduate School of    \n");
        bw.write("#                           Science and Engineering                 \n");
        bw.write("#                                                                   \n");
        bw.write("# All rights reserved.                                              \n");
        bw.write("#                                                                   \n");
        bw.write("# Redistribution and use in source and binary forms, with or        \n");
        bw.write("# without modification, are permitted provided that the following   \n");
        bw.write("# conditions are met:                                               \n");
        bw.write("#                                                                   \n");
        bw.write("# - Redistributions of source code must retain the above copyright  \n");
        bw.write("#   notice, this list of conditions and the following disclaimer.   \n");
        bw.write("# - Redistributions in binary form must reproduce the above         \n");
        bw.write("#   copyright notice, this list of conditions and the following     \n");
        bw.write("#   disclaimer in the documentation and/or other materials provided \n");
        bw.write("#   with the distribution.                                          \n");
        bw.write("# - Neither the name of the HTS working group nor the names of its  \n");
        bw.write("#   contributors may be used to endorse or promote products derived \n");
        bw.write("#   from this software without specific prior written permission.   \n");
        bw.write("#                                                                   \n");
        bw.write("# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND            \n");
        bw.write("# CONTRIBUTORS \"AS IS\" AND ANY EXPRESS OR IMPLIED WARRANTIES,       \n");
        bw.write("# INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF          \n");
        bw.write("# MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE          \n");
        bw.write("# DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS \n");
        bw.write("# BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,          \n");
        bw.write("# EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED   \n");
        bw.write("# TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,     \n");
        bw.write("# DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON \n");
        bw.write("# ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,   \n");
        bw.write("# OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY    \n");
        bw.write("# OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE           \n");
        bw.write("# POSSIBILITY OF SUCH DAMAGE.                                       \n");
        bw.write("# ----------------------------------------------------------------- \n");
        bw.write("# Settings ============================== \n");
        bw.write("$spkr = 'm001'; \n");
        bw.write("$data = 'portuguese'; \n");
        bw.write("$qnum = '001'; \n");
        bw.write("$ver  = '1'; \n");
        bw.write(" \n");
        bw.write("@SET        = ('cmp','dur'); \n");
        bw.write("@cmp        = ('mgc','lf0'); \n");
        bw.write("@dur        = ('dur'); \n");
        bw.write("$ref{'cmp'} = \\@cmp; \n");
        bw.write("$ref{'dur'} = \\@dur; \n");
        bw.write(" \n");
        bw.write("%vflr = ('mgc' => '0.01', # variance floors \n");
        bw.write("         'lf0' => '0.01', \n");
        bw.write("         'dur' => '0.01'); \n");
        bw.write(" \n");
        bw.write("%thr  = ('mgc' => '000',  # minimum state occupancy \n");
        bw.write("         'lf0' => '000', \n");
        bw.write("         'dur' => '000'); \n");
        bw.write(" \n");
        bw.write("%mdlf = ('mgc' => '1.0',  # tree size control param. for MDL \n");
        bw.write("         'lf0' => '1.0', \n");
        bw.write("         'dur' => '1.0'); \n");
        bw.write(" \n");
        bw.write("%mocc = ('mgc' => '10.0',  # minimum occupancy counts \n");
        bw.write("         'lf0' => '10.0', \n");
        bw.write("         'dur' => ' 5.0'); \n");
        bw.write(" \n");
        bw.write("%gam  = ('mgc' => '000',  # stats load threshold \n");
        bw.write("         'lf0' => '000', \n");
        bw.write("         'dur' => '000'); \n");
        bw.write(" \n");
        bw.write("%t2s  = ('mgc' => 'cmp',  # feature type to mmf conversion \n");
        bw.write("         'lf0' => 'cmp', \n");
        bw.write("         'dur' => 'dur'); \n");
        bw.write(" \n");
        bw.write("%strb = ('mgc' => '1',  # stream start \n");
        bw.write("         'lf0' => '2', \n");
        bw.write("         'dur' => '1'); \n");
        bw.write(" \n");
        bw.write("%stre = ('mgc' => '1',  # stream end \n");
        bw.write("         'lf0' => '4', \n");
        bw.write("         'dur' => '5'); \n");
        bw.write(" \n");
        bw.write("%msdi = ('mgc' => '0',  # msd information \n");
        bw.write("         'lf0' => '1', \n");
        bw.write("         'dur' => '0'); \n");
        bw.write(" \n");
        bw.write("%strw = ('mgc' => '1.0',  # stream weights \n");
        bw.write("         'lf0' => '1.0', \n");
        bw.write("         'dur' => '1.0'); \n");
        bw.write(" \n");
        bw.write("%ordr = ('mgc' => '25',  # feature order \n");
        bw.write("         'lf0' => '1', \n");
        bw.write("         'dur' => '5'); \n");
        bw.write(" \n");
        bw.write("%nwin = ('mgc' => '3',  # number of windows \n");
        bw.write("         'lf0' => '3', \n");
        bw.write("         'dur' => '0'); \n");
        bw.write(" \n");
        bw.write("%nblk = ('mgc' => '3',   # number of blocks for transforms \n");
        bw.write("         'lf0' => '1', \n");
        bw.write("         'dur' => '1'); \n");
        bw.write(" \n");
        bw.write("%band = ('mgc' => '24',   # band width for transforms \n");
        bw.write("         'lf0' => '0', \n");
        bw.write("         'dur' => '0'); \n");
        bw.write("   \n");
        bw.write("#%mdcp = ('dy' => 'd',   # model copy \n");
        bw.write("#         'A'  => 'a', \n");
        bw.write("#         'I'  => 'i', \n");
        bw.write("#         'U'  => 'u', \n");
        bw.write("#         'E'  => 'e', \n");
        bw.write("#         'O'  => 'o'); \n");
        bw.write(" \n");
        bw.write(" \n");
        bw.write("# Speech Analysis/Synthesis Setting ============== \n");
        bw.write("# speech analysis \n");
        bw.write("$sr = " + mgcParameters.getSampfreq() + ";   # sampling rate (Hz) \n");
        bw.write("$fs = " + mgcParameters.getFrameshift() + ";      # frame period (point) \n");
        bw.write("$fw = " + mgcParameters.getFreqwarp() + ";    # frequency warping \n");
        bw.write("$gm = " + mgcParameters.getGamma() + ";   # pole/zero representation weight \n");
        bw.write("$lg = " + mgcParameters.getLngain() + ";   # use log gain instead of linear gain \n");
        bw.write("$fr = $fs/$sr; # frame period (sec) \n");
        bw.write(" \n");
        bw.write("# speech synthesis \n");
        bw.write("$pf = 1.4;     # postfiltering factor \n");
        bw.write("$fl = 4096;    # length of impulse response \n");
        bw.write("$co = 2047;    # order of cepstrum to approximate mel-generalized cepstrum \n");
        bw.write(" \n");
        bw.write(" \n");
        bw.write("# Modeling/Generation Setting ============== \n");
        bw.write("# modeling \n");
        bw.write("$nState = 5;                # number of states \n");
        bw.write("$nIte   = 5;                # number of iterations for embedded training \n");
        bw.write("$beam   = '1500 100 5000';  # initial, inc, and upper limit of beam width \n");
        bw.write("$maxdev = 10;                # max standard dev coef to control HSMM maximum duration \n");
        bw.write("$mindur = 5;                # min state duration to be evaluated \n");
        bw.write("$wf     = 3;                # mixture weight flooring \n");
        bw.write(" \n");
        bw.write("# generation \n");
        bw.write("$maxEMiter  = 20;      # max EM iteration \n");
        bw.write("$EMepsilon  = 0.0001;  # convergence factor for EM iteration \n");
        bw.write("$useGV      = 1;       # turn on GV \n");
        bw.write("$maxGViter  = 50;      # max GV iteration \n");
        bw.write("$GVepsilon  = 0.0001;  # convergence factor for GV iteration \n");
        bw.write("$minEucNorm = 0.01;    # minimum Euclid norm for GV iteration \n");
        bw.write("$stepInit   = 1.0;     # initial step size \n");
        bw.write("$stepInc    = 1.2;     # step size acceleration factor \n");
        bw.write("$stepDec    = 0.5;     # step size deceleration factor \n");
        bw.write("$hmmWeight  = 1.0;     # weight for HMM output prob. \n");
        bw.write("$gvWeight   = 1.0;     # weight for GV output prob. \n");
        bw.write("$optKind    = 'NEWTON'; # optimization method (STEEPEST, NEWTON, or LBFGS) \n");
        bw.write(" \n");
        bw.write(" \n");
        bw.write("# Directories & Commands =============== \n");
        bw.write("# project directories \n");
        bw.write("$prjdir = '" + projectDir + "'; \n");
        bw.write(" \n");
        bw.write("# HTS commands \n");
        bw.write("$HCOMPV = '" + resourcesPath + File.separator + "hts" + File.separator + "HCompV'; \n");
        bw.write("$HINIT  = '" + resourcesPath + File.separator + "hts" + File.separator + "HInit'; \n");
        bw.write("$HREST  = '" + resourcesPath + File.separator + "hts" + File.separator + "HRest'; \n");
        bw.write("$HEREST = '" + resourcesPath + File.separator + "hts" + File.separator + "HERest'; \n");
        bw.write("$HHED   = '" + resourcesPath + File.separator + "hts" + File.separator + "HHEd'; \n");
        bw.write("$HMGENS = '" + resourcesPath + File.separator + "hts" + File.separator + "HMGenS'; \n");
        bw.write("$ENGINE = '" + resourcesPath + File.separator + "hts" + File.separator + "hts_engine'; \n");
        bw.write(" \n");
        bw.write("# SPTK commands \n");
        bw.write("$X2X      = '" + resourcesPath + File.separator + "sptk" + File.separator + "x2x'; \n");
        bw.write("$FREQT    = '" + resourcesPath + File.separator + "sptk" + File.separator + "freqt'; \n");
        bw.write("$C2ACR    = '" + resourcesPath + File.separator + "sptk" + File.separator + "c2acr'; \n");
        bw.write("$VOPR     = '" + resourcesPath + File.separator + "sptk" + File.separator + "vopr'; \n");
        bw.write("$MC2B     = '" + resourcesPath + File.separator + "sptk" + File.separator + "mc2b'; \n");
        bw.write("$SOPR     = '" + resourcesPath + File.separator + "sptk" + File.separator + "sopr'; \n");
        bw.write("$B2MC     = '" + resourcesPath + File.separator + "sptk" + File.separator + "b2mc'; \n");
        bw.write("$EXCITE   = '" + resourcesPath + File.separator + "sptk" + File.separator + "excite'; \n");
        bw.write("$LSP2LPC  = '" + resourcesPath + File.separator + "sptk" + File.separator + "lsp2lpc'; \n");
        bw.write("$MGC2MGC  = '" + resourcesPath + File.separator + "sptk" + File.separator + "mgc2mgc'; \n");
        bw.write("$MGLSADF  = '" + resourcesPath + File.separator + "sptk" + File.separator + "mglsadf'; \n");
        bw.write("$MERGE    = '" + resourcesPath + File.separator + "sptk" + File.separator + "merge'; \n");
        bw.write("$BCP      = '" + resourcesPath + File.separator + "sptk" + File.separator + "bcp'; \n");
        bw.write("$LSPCHECK = '" + resourcesPath + File.separator + "sptk" + File.separator + "lspcheck'; \n");
        bw.write(" \n");
        bw.write("# SoX (to add RIFF header) \n");
        bw.write("$SOX = '/usr/bin/sox'; \n");
        bw.write(" \n");
        bw.write(" \n");
        bw.write("# Switch ================================ \n");
        bw.write("$MKEMV = 1;  # preparing environments \n");
        bw.write("$HCMPV = 1;  # computing a global variance \n");
        bw.write("$IN_RE = 1;  # initialization & reestimation \n");
        bw.write("$MMMMF = 1;  # making a monophone mmf \n");
        bw.write("$ERST0 = 1;  # embedded reestimation (monophone) \n");
        bw.write("$MN2FL = 1;  # copying monophone mmf to fullcontext one \n");
        bw.write("$ERST1 = 1;  # embedded reestimation (fullcontext) \n");
        bw.write("$CXCL1 = 1;  # tree-based context clustering \n");
        bw.write("$ERST2 = 1;  # embedded reestimation (clustered) \n");
        bw.write("$UNTIE = 1;  # untying the parameter sharing structure \n");
        bw.write("$ERST3 = 1;  # embedded reestimation (untied) \n");
        bw.write("$CXCL2 = 1;  # tree-based context clustering \n");
        bw.write("$ERST4 = 1;  # embedded reestimation (re-clustered) \n");
        bw.write("$MKUN1 = 1;  # making unseen models (1mix) \n");
        bw.write("$PGEN1 = 1;  # generating speech parameter sequences (1mix) \n");
        bw.write("$WGEN1 = 1;  # synthesizing waveforms (1mix) \n");
        bw.write("$CONVM = 1;  # converting mmfs to the hts_engine file format \n");
        bw.write("$ENGIN = 1;  # synthesizing waveforms using hts_engine \n");
        bw.write("$UPMIX = 1;  # increasing the number of mixture components (1mix -> 2mix) \n");
        bw.write("$SEMIT = 1;  # semi-tied covariance matrices \n");
        bw.write("$MKUNS = 1;  # making unseen models (stc) \n");
        bw.write("$PGENS = 1;  # generating speech parameter sequences (stc) \n");
        bw.write("$WGENS = 1;  # synthesizing waveforms (stc) \n");
        bw.write("$ERST5 = 1;  # embedded reestimation (2mix) \n");
        bw.write("$MKUN2 = 1;  # making unseen models (2mix) \n");
        bw.write("$PGEN2 = 1;  # generating speech parameter sequences (2mix) \n");
        bw.write("$WGEN2 = 1;  # synthesizing waveforms (2mix) \n");
        bw.write(" \n");
        bw.write("1; \n");
        bw.write(" \n");
        bw.close();
    }
}
