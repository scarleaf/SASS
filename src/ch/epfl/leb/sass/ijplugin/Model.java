/*
 * Copyright (C) 2017 Laboratory of Experimental Biophysics
 * Ecole Polytechnique Federale de Lausanne
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.epfl.leb.sass.ijplugin;

import ch.epfl.leb.sass.simulator.generators.realtime.Microscope;
import ch.epfl.leb.sass.simulator.generators.realtime.components.Camera;
import ch.epfl.leb.sass.simulator.generators.realtime.components.Laser;
import ch.epfl.leb.sass.simulator.generators.realtime.components.Objective;
import ch.epfl.leb.sass.simulator.generators.realtime.components.Stage;
import ch.epfl.leb.sass.simulator.generators.realtime.fluorophores.dynamics.SimpleDynamics;
import ch.epfl.leb.sass.simulator.generators.realtime.fluorophores.commands.*;
import ch.epfl.leb.sass.simulator.generators.realtime.psfs.*;
import ch.epfl.leb.sass.simulator.generators.realtime.obstructors.commands.GenerateFiducialsRandom2D;
import ch.epfl.leb.sass.simulator.generators.realtime.backgrounds.commands.*;
import ij.IJ;
import java.io.File;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;


/**
 * Model for the InitializeSimulation window.
 * 
 * @author Kyle M. Douglass
 */
public class Model implements Serializable {
    
    private int cameraNX;
    private int cameraNY;
    private double cameraReadoutNoise;
    private double cameraDarkCurrent;
    private double cameraQuantumEfficiency;
    private double cameraAduPerElectron;
    private int cameraEmGain;
    private int cameraBaseline;
    private double cameraPixelSize;
    private double cameraThermalNoise;
    
    private double objectiveNa;
    private double objectiveMag;
    
    private double fluorophoreSignal;
    private double fluorophoreWavelength;
    private double fluorophoreTOn;
    private double fluorophoreTOff;
    private double fluorophoreTBl;
    
    private double laserMinPower;
    private double laserMaxPower;
    private double laserCurrentPower;
    
    private double stageX;
    private double stageY;
    private double stageZ;
    
    private String emittersCurrentSelection;
    private int emittersRandomNumber;
    private int emittersGridSpacing;
    private String emittersCsvFile;
    private String emittersRandomButtonText;
    private String emittersGridButtonText;
    private String emittersCsvFileButtonText;
    private boolean emitters3DCheckBoxEnabled;
    private double emitters3DMinZ;
    private double emitters3DMaxZ;
    
    private int fiducialsNumber;
    private double fiducialsSignal;
    
    private String backgroundCurrentSelection;
    private float backgroundUniformSignal;
    private double backgroundRandomFeatureSize;
    private float backgroundRandomMinValue;
    private float backgroundRandomMaxValue;
    private int backgroundRandomSeed;
    private String backgroundTifFile;
    private String backgroundRandomButtonText;
    private String backgroundUniformButtonText;
    private String backgroundTifFileButtonText;
    
    private String psfCurrentSelection;
    private String psfGaussian2dText;
    private String psfGaussian3dText;
    private int psfGibsonLanniNumBasis;
    private int psfGibsonLanniNumSamples;
    private int psfGibsonLanniOversampling;
    private int psfGibsonLanniSizeX;
    private int psfGibsonLanniSizeY;
    private double psfGibsonLanniNs;
    private double psfGibsonLanniNg0;
    private double psfGibsonLanniNg;
    private double psfGibsonLanniNi0;
    private double psfGibsonLanniNi;
    private double psfGibsonLanniTi0;
    private double psfGibsonLanniTg0;
    private double psfGibsonLanniTg;
    private double psfGibsonLanniResPsf;
    private double psfGibsonLanniResPsfAxial;
    private String psfGibsonLanniSolver;
    private int psfGibsonLanniMaxRadius;
    private String psfGibsonLanniText;
    
    // Getters
    //--------------------------------------------------------------------------
    public int getCameraNX() { return cameraNX; }
    public int getCameraNY() { return cameraNY; }
    public double getCameraReadoutNoise() { return cameraReadoutNoise; }
    public double getCameraDarkCurrent() { return cameraDarkCurrent; }
    public double getCameraQuantumEfficiency() {
        return cameraQuantumEfficiency;
    }
    public double getCameraAduPerElectron() { return cameraAduPerElectron; }
    public int getCameraEmGain() { return cameraEmGain; }
    public int getCameraBaseline() { return cameraBaseline; }
    public double getCameraPixelSize() { return cameraPixelSize; }
    public double getCameraThermalNoise() { return cameraThermalNoise; }
    
    public double getObjectiveNa() { return objectiveNa; }
    public double getObjectiveMag() { return objectiveMag; }
    
    public double getFluorophoreSignal() { return fluorophoreSignal; }
    public double getFluorophoreWavelength() { return fluorophoreWavelength; }
    public double getFluorophoreTOn() { return fluorophoreTOn; }
    public double getFluorophoreTOff() { return fluorophoreTOff; }
    public double getFluorophoreTBl() { return fluorophoreTBl; }
    
    public double getLaserMinPower() { return laserMinPower; }
    public double getLaserMaxPower() { return laserMaxPower; }
    public double getLaserCurrentPower() { return laserCurrentPower; }
    
    public double getStageX() { return stageX; }
    public double getStageY() { return stageY; }
    public double getStageZ() { return stageZ; }
    
    public String getEmittersCurrentSelection() {
        return emittersCurrentSelection;
    }
    public int getEmittersRandomNumber() { return emittersRandomNumber; }
    public int getEmittersGridSpacing() { return emittersGridSpacing; }
    public String getEmittersCsvFile() { return emittersCsvFile; }
    public String getEmittersRandomButtonText() {
        return emittersRandomButtonText;
    }
    public String getEmittersGridButtonText() {
        return emittersGridButtonText;
    }
    public String getEmittersCsvFileButtonText() {
        return emittersCsvFileButtonText;
    }
    public boolean getEmitters3DCheckBoxEnabled() {
        return emitters3DCheckBoxEnabled;
    }
    public double getEmitters3DMinZ() { return emitters3DMinZ; }
    public double getEmitters3DMaxZ() { return emitters3DMaxZ; }
    
    public int getFiducialsNumber() { return fiducialsNumber; }
    public double getFiducialsSignal() { return fiducialsSignal;}
    
    public String getBackgroundCurrentSelection() {
        return backgroundCurrentSelection;
    }
    public float getBackgroundUniformSignal() { return backgroundUniformSignal; }
    public String getBackgroundUniformButtonText() {
        return backgroundUniformButtonText;
    }
    public double getBackgroundRandomFeatureSize() {
        return backgroundRandomFeatureSize;
    }
    public float getBackgroundRandomMinValue() { return backgroundRandomMinValue; }
    public float getBackgroundRandomMaxValue() { return backgroundRandomMaxValue; }
    public int getBackgroundRandomSeed() { return backgroundRandomSeed; }
    public String getBackgroundRandomButtonText() {
        return backgroundRandomButtonText;}
    public String getBackgroundTifFile() { return backgroundTifFile; }
    public String getBackgroundTifFileButtonText() {
        return backgroundTifFileButtonText;
    }
    public String getPsfCurrentSelection() { return psfCurrentSelection; }
    public String getPsfGaussian2dText() { return psfGaussian2dText; }
    public String getPsfGaussian3dText() { return psfGaussian3dText; }
    public int getPsfGibsonLanniNumBasis() { return psfGibsonLanniNumBasis; }
    public int getPsfGibsonLanniNumSamples() { return psfGibsonLanniNumSamples; }
    public int getPsfGibsonLanniOversampling() { return psfGibsonLanniOversampling; }
    public int getPsfGibsonLanniSizeX() { return psfGibsonLanniSizeX; }
    public int getPsfGibsonLanniSizeY() { return psfGibsonLanniSizeY; }
    public double getPsfGibsonLanniNs() { return psfGibsonLanniNs; }
    public double getPsfGibsonLanniNg0() { return psfGibsonLanniNg0; }
    public double getPsfGibsonLanniNg() { return psfGibsonLanniNg; }
    public double getPsfGibsonLanniNi0() { return psfGibsonLanniNi0; }
    public double getPsfGibsonLanniNi() { return psfGibsonLanniNi; }
    public double getPsfGibsonLanniTi0() { return psfGibsonLanniTi0; }
    public double getPsfGibsonLanniTg0() { return psfGibsonLanniTg0; }
    public double getPsfGibsonLanniTg() { return psfGibsonLanniTg; }
    public double getPsfGibsonLanniResPsf() { return psfGibsonLanniResPsf; }
    public double getPsfGibsonLanniResPsfAxial() { return psfGibsonLanniResPsfAxial; }
    public String getPsfGibsonLanniSolver() { return psfGibsonLanniSolver; }
    public int getPsfGibsonLanniMaxRadius() { return psfGibsonLanniMaxRadius; }
    public String getPsfGibsonLanniText() {
        return psfGibsonLanniText;
    }
    
    // Setters
    //--------------------------------------------------------------------------
    public void setCameraNX(int nX) { cameraNX = nX; }
    public void setCameraNY(int nY) { cameraNY = nY; }
    public void setCameraReadoutNoise(double readoutNoise) {
        cameraReadoutNoise = readoutNoise;
    }
    public void setCameraDarkCurrent(double darkCurrent) {
        cameraDarkCurrent = darkCurrent;
    }
    public void setCameraQuantumEfficiency(double quantumEfficiency) {
        cameraQuantumEfficiency = quantumEfficiency;
    }
    public void setCameraAduPerElectron(double aduPerElectron) {
        cameraAduPerElectron = aduPerElectron;
    }
    public void setCameraEmGain(int emGain) { cameraEmGain = emGain; }
    public void setCameraBaseline(int baseline) { cameraBaseline = baseline; }
    public void setCameraPixelSize(double pixelSize) {
        cameraPixelSize = pixelSize;
    }
    public void setCameraThermalNoise(double thermalNoise) {
        cameraThermalNoise = thermalNoise;
    }
    
    public void setObjectiveNa(double na) { objectiveNa = na; }
    public void setObjectiveMag(double mag) { objectiveMag = mag; }
    
    public void setFluorophoreSignal(double signal) {
        fluorophoreSignal = signal;
    }
    public void setFluorophoreWavelength(double wavelength) {
        fluorophoreWavelength = wavelength;
    }
    public void setFluorophoreTOn(double tOn) { fluorophoreTOn = tOn; }
    public void setFluorophoreTOff(double tOff) { fluorophoreTOff = tOff; }
    public void setFluorophoreTBl(double tBl) { fluorophoreTBl = tBl; }
    
    public void setLaserMinPower(double minPower) { laserMinPower = minPower; }
    public void setLaserMaxPower(double maxPower) { laserMaxPower = maxPower; }
    public void setLaserCurrentPower(double currentPower) {
        laserCurrentPower = currentPower;
    }
    
    public void setStageX(double x) { stageX = x; }
    public void setStageY(double y) { stageY = y; }
    public void setStageZ(double z) { stageZ = z; }
    
    public void setEmittersCurrentSelection(String currentSelection) {
        emittersCurrentSelection = currentSelection;
    }
    public void setEmittersRandomNumber(int number) {
        emittersRandomNumber = number;
    }
    public void setEmittersGridSpacing(int spacing) {
        emittersGridSpacing = spacing;
    }
    public void setEmittersCsvFile(String filename) {
        emittersCsvFile = filename;
    }
    public void setEmittersRandomButtonText(String text) {
        emittersRandomButtonText = text;
    }
    public void setEmittersGridButtonText(String text) {
        emittersGridButtonText = text;
    }
    public void setEmittersCsvFileButtonText(String text) {
        emittersCsvFileButtonText = text;
    }
    public void setEmitters3DCheckBoxEnabled(boolean enabled) {
        emitters3DCheckBoxEnabled = enabled;
    }
    public void setEmitters3DMinZ(double min) {
        emitters3DMinZ = min;
    }
    public void setEmitters3DMaxZ(double max) {
        emitters3DMaxZ = max;
    }
    
    public void setFiducialsNumber(int number) {
        fiducialsNumber = number;
    }
    public void setFiducialsSignal(double signal) {
        fiducialsSignal = signal; }
    
    public void setBackgroundCurrentSelection(String currentSelection) {
        backgroundCurrentSelection = currentSelection;
    }
    public void setBackgroundUniformSignal(float signal) {
        backgroundUniformSignal = signal;
    }
    public void setBackgroundUniformButtonText(String text) {
        backgroundUniformButtonText = text;
    }
    public void setBackgroundRandomFeatureSize(double featureSize) {
        backgroundRandomFeatureSize = featureSize;
    }
    public void setBackgroundRandomMinValue(float minValue) {
        backgroundRandomMinValue = minValue;
    }
    public void setBackgroundRandomMaxValue(float maxValue) {
        backgroundRandomMaxValue = maxValue;
    }
    public void setBackgroundRandomSeed(int seed) {
        backgroundRandomSeed = seed;
    }
    public void setBackgroundRandomButtonText(String text) {
        backgroundRandomButtonText = text;
    }
    public void setBackgroundTifFile(String filename) {
        backgroundTifFile = filename;
    }    
    public void setBackgroundTifFileButtonText(String text) {
        backgroundTifFileButtonText = text;
    }
    public void setPsfCurrentSelection(String text) {
        psfCurrentSelection = text;
    }
    public void setPsfGaussian2dText(String text) {
        psfGaussian2dText = text;
    }
    public void setPsfGaussian3dText(String text) {
        psfGaussian3dText = text;
    }
    public void setPsfGibsonLanniNumBasis(int numBasis) {
        psfGibsonLanniNumBasis = numBasis;
    }
    public void setPsfGibsonLanniNumSamples(int numSamples) {
        psfGibsonLanniNumSamples = numSamples;
    }
    public void setPsfGibsonLanniOversampling(int oversampling) {
        psfGibsonLanniOversampling = oversampling; }
    public void setPsfGibsonLanniSizeX(int sizeX) {  psfGibsonLanniSizeX = sizeX; }
    public void setPsfGibsonLanniSizeY(int sizeY) {  psfGibsonLanniSizeY = sizeY; }
    public void setPsfGibsonLanniNs(double ns) {  psfGibsonLanniNs = ns; }
    public void setPsfGibsonLanniNg0(double ng0) { psfGibsonLanniNg0 = ng0; }
    public void setPsfGibsonLanniNg(double ng) {  psfGibsonLanniNg = ng; }
    public void setPsfGibsonLanniNi0(double ni0) {  psfGibsonLanniNi0 = ni0; }
    public void setPsfGibsonLanniNi(double ni) {  psfGibsonLanniNi =  ni; }
    public void setPsfGibsonLanniTi0(double ti0) {  psfGibsonLanniTi0 = ti0; }
    public void setPsfGibsonLanniTg0(double tg0) {  psfGibsonLanniTg0 = tg0; }
    public void setPsfGibsonLanniTg(double tg) {  psfGibsonLanniTg = tg; }
    public void setPsfGibsonLanniResPsf(double resPsf) {
        psfGibsonLanniResPsf = resPsf;
    }
    public void setPsfGibsonLanniResPsfAxial(double resPsfAxial) {
        psfGibsonLanniResPsfAxial = resPsfAxial;
    }
    public void setPsfGibsonLanniSolver(String solver) {
        psfGibsonLanniSolver = solver;
    }
    public void setPsfGibsonLanniMaxRadius(int maxRadius) {
        psfGibsonLanniMaxRadius = maxRadius;
    }
    public void setPsfGibsonLanniText(String text) {
        psfGibsonLanniText = text;
    }
    
    /**
     * Builds a microscope from the model parameters.
     * @return A new microscope built from the model parameters.
     */
    public Microscope build() {
        Camera.Builder cameraBuilder = new Camera.Builder();
        Objective.Builder objectiveBuilder = new Objective.Builder();
        Stage.Builder stageBuilder = new Stage.Builder();
        SimpleDynamics.Builder fluorPropBuilder = new SimpleDynamics.Builder();
        Laser.Builder laserBuilder = new Laser.Builder();
        PSFBuilder psfBuilder = null;
        FluorophoreCommandBuilder fluorPosBuilder = null;
        GenerateFiducialsRandom2D.Builder fidBuilder = 
                new GenerateFiducialsRandom2D.Builder();
        BackgroundCommandBuilder backgroundBuilder = null;
        
        cameraBuilder.nX(cameraNX);
        cameraBuilder.nY(cameraNY);
        cameraBuilder.readoutNoise(cameraReadoutNoise);
        cameraBuilder.darkCurrent(cameraDarkCurrent);
        cameraBuilder.quantumEfficiency(cameraQuantumEfficiency);
        cameraBuilder.aduPerElectron(cameraAduPerElectron);
        cameraBuilder.emGain(cameraEmGain);
        cameraBuilder.baseline(cameraBaseline);
        cameraBuilder.pixelSize(cameraPixelSize);

        objectiveBuilder.NA(objectiveNa);
        objectiveBuilder.mag(objectiveMag);

        stageBuilder.x(0);
        stageBuilder.y(0);
        stageBuilder.z(stageZ);

        fluorPropBuilder.signal(fluorophoreSignal);
        fluorPropBuilder.wavelength(fluorophoreWavelength);
        fluorPropBuilder.tOn(fluorophoreTOn);
        fluorPropBuilder.tOff(fluorophoreTOff);
        fluorPropBuilder.tBl(fluorophoreTBl);

        fidBuilder.numFiducials(fiducialsNumber); // Set to zero if you don't want fiducials
        fidBuilder.brightness(fiducialsSignal);
        
        laserBuilder.currentPower(laserCurrentPower);
        laserBuilder.minPower(laserMinPower);
        laserBuilder.maxPower(laserMaxPower);

        if (emittersCurrentSelection.equals(emittersRandomButtonText) & !(emitters3DCheckBoxEnabled)) {
            // Random 2D fluorophore distributions
            try {
                GenerateFluorophoresRandom2D.Builder tempPosBuilder = new GenerateFluorophoresRandom2D.Builder();
                tempPosBuilder.numFluors(emittersRandomNumber);
                fluorPosBuilder = tempPosBuilder;
            } catch (Exception ex) {
                IJ.showMessage("Error in emitter position parsing.");
            }
        } else if (emittersCurrentSelection.equals(emittersGridButtonText) & !(emitters3DCheckBoxEnabled)) {
            // Fluorophore distributions in 2D on a square grid
            try {
                GenerateFluorophoresGrid2D.Builder tempPosBuilder = new GenerateFluorophoresGrid2D.Builder();
                tempPosBuilder.spacing(emittersGridSpacing);
                fluorPosBuilder = tempPosBuilder;
            } catch (Exception ex) {
                IJ.showMessage("Error in emitter position parsing.");
            }
        } else if (emittersCurrentSelection.equals(emittersRandomButtonText) & emitters3DCheckBoxEnabled) {
            // Random 3D fluorophore distributions
            try {
                GenerateFluorophoresRandom3D.Builder tempPosBuilder = new GenerateFluorophoresRandom3D.Builder();
                tempPosBuilder.numFluors(emittersRandomNumber);
                tempPosBuilder.zLow(emitters3DMinZ);
                tempPosBuilder.zHigh(emitters3DMaxZ);
                fluorPosBuilder = tempPosBuilder;
            } catch (Exception ex) {
                IJ.showMessage("Error in emitter position parsing.");
            }
        } else if (emittersCurrentSelection.equals(emittersGridButtonText) & !(emitters3DCheckBoxEnabled)) {
            // Fluorophore distributions in 3D on a square grid
            try {
                GenerateFluorophoresGrid3D.Builder tempPosBuilder = new GenerateFluorophoresGrid3D.Builder();
                tempPosBuilder.spacing(emittersGridSpacing);
                tempPosBuilder.zLow(emitters3DMinZ);
                tempPosBuilder.zHigh(emitters3DMaxZ);
                fluorPosBuilder = tempPosBuilder;
            } catch (Exception ex) {
                IJ.showMessage("Error in emitter position parsing.");
            } 
        } else if (emittersCurrentSelection.equals(emittersCsvFileButtonText)) {
            // Parse fluorophore positions from a CSV file
            GenerateFluorophoresFromCSV.Builder tempPosBuilder = new GenerateFluorophoresFromCSV.Builder();
            tempPosBuilder.file(new File(emittersCsvFile));
            tempPosBuilder.rescale(false);
            fluorPosBuilder = tempPosBuilder;
        }

        if (backgroundCurrentSelection.equals(backgroundUniformButtonText)) {
            try {
                GenerateUniformBackground.Builder tempBuilder = new GenerateUniformBackground.Builder();
                tempBuilder.backgroundSignal(backgroundUniformSignal);
                backgroundBuilder = tempBuilder;
            } catch (NumberFormatException ex) {
                IJ.showMessage("Error in parsing of numerical values.");
            } catch (Exception ex) {
                IJ.showMessage("Error in device component intialization.");
            }
        } else if (backgroundCurrentSelection.equals(backgroundTifFileButtonText)) {
            try {
                GenerateBackgroundFromFile.Builder tempBuilder = new GenerateBackgroundFromFile.Builder();
                tempBuilder.file(new File(backgroundTifFile));
                backgroundBuilder = tempBuilder;
            } catch (ArrayIndexOutOfBoundsException ex) {
                IJ.showMessage("Error in background loading. The image is not large enough.");
            } catch (Exception ex) {
                IJ.showMessage("Error in background loading.");
            }
        } else if (backgroundCurrentSelection.equals(backgroundRandomButtonText)) {
            try {
                GenerateRandomBackground.Builder tempBuilder = new GenerateRandomBackground.Builder();
                tempBuilder.featureSize(backgroundRandomFeatureSize);
                tempBuilder.min(backgroundRandomMinValue);
                tempBuilder.max(backgroundRandomMaxValue);
                tempBuilder.seed(backgroundRandomSeed);
                backgroundBuilder = tempBuilder;
                } catch (NumberFormatException ex) {
                IJ.showMessage("Error in parsing of numerical values.");
            } catch (Exception ex) {
                IJ.showMessage("Error in device component intialization.");
            }
        }
        
        if (psfCurrentSelection.equals(psfGaussian2dText)) {
            psfBuilder = new Gaussian2D.Builder();
        } else if (psfCurrentSelection.equals(psfGaussian3dText)) {
            psfBuilder = new Gaussian3D.Builder();
        } else if (psfCurrentSelection.equals(psfGibsonLanniText)) {
            GibsonLanniPSF.Builder tempBuilder = new GibsonLanniPSF.Builder();
            tempBuilder.numBasis(psfGibsonLanniNumBasis)
                    .numSamples(psfGibsonLanniNumSamples)
                    .oversampling(psfGibsonLanniOversampling)
                    .sizeX(psfGibsonLanniSizeX)
                    .sizeY(psfGibsonLanniSizeY)
                    .ns(psfGibsonLanniNs)
                    .ng0(psfGibsonLanniNg0)
                    .ng(psfGibsonLanniNg)
                    .ni0(psfGibsonLanniNi0)
                    .ni(psfGibsonLanniNi)
                    .ti0(psfGibsonLanniTi0)
                    .tg0(psfGibsonLanniTg0)
                    .tg(psfGibsonLanniTg)
                    .resPSF(psfGibsonLanniResPsf)
                    .resPSFAxial(psfGibsonLanniResPsfAxial)
                    .solver(psfGibsonLanniSolver)
                    .maxRadius(psfGibsonLanniMaxRadius);
            psfBuilder = tempBuilder;
            
        }
        
        return new Microscope(
            cameraBuilder,
            laserBuilder,
            objectiveBuilder,
            psfBuilder,
            stageBuilder,
            fluorPosBuilder,
            fluorPropBuilder,
            fidBuilder,
            backgroundBuilder
        );
    }
    
    /**
     * Saves the model's state to a file.
     * 
     * @param fileOut The output stream to the file.
     */
    public void write(FileOutputStream fileOut) {
         try {
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
         } catch (IOException ex) {
            ex.printStackTrace();
      }
    }
    
    /**
     * Loads a model from a file.
     * 
     * @param fileIn The input stream from the file.
     */
    public static Model read(FileInputStream fileIn) {
        Model model = null;
        try {
            ObjectInputStream in = new ObjectInputStream(fileIn);
            model = (Model) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException ex) {
           ex.printStackTrace();
        } catch (ClassNotFoundException c) {
           c.printStackTrace();
        }
        
        return model;
    }
}
