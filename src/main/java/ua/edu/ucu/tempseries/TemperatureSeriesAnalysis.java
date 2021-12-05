package ua.edu.ucu.tempseries;

public class TemperatureSeriesAnalysis {
    private double[] tempsVal = {};
    private int size = 0;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.tempsVal = temperatureSeries.clone();
        this.size = this.tempsVal.length;
    }

    public double average() {
        if (this.size  == 0) {
            throw new IllegalArgumentException("Empty array");
        } else {
            double sumElems = 0;
            for (int i = 0; i < this.size; i++) {
                sumElems += tempsVal[i];
            }

            return sumElems/this.size;
        }
    }

    public double deviation() {
        if (this.size  == 0) {
            throw new IllegalArgumentException("Empty array");
        } else {
            double mean = this.average();
            double temprSum = 0;

            for (int i = 0; i < this.size; i++) {
                temprSum += java.lang.Math.pow(tempsVal[i] - mean, 2);
            }

            if (temprSum == 0) {
                return 0.0;
            } else {
                return java.lang.Math.pow(temprSum/(this.size  - 1), 0.5);
            }
        }
    }

    public double min() {
        if (this.size  == 0) {
            throw new IllegalArgumentException("Empty array");
        } else {
            double minVal = tempsVal[0];
            for (int i = 1; i < this.size; i++) {
                if (minVal > tempsVal[i]) {
                    minVal = tempsVal[i];
                }
            }

            return minVal;
        }
    }

    public double max() {
        if (this.size == 0) {
            throw new IllegalArgumentException("Empty array");
        } else {
            double maxVal = tempsVal[0];
            for (int i = 1; i < this.size; i++) {
                if (maxVal < tempsVal[i]) {
                    maxVal = tempsVal[i];
                }
            }

            return maxVal;
        }
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (this.size  == 0) {
            throw new IllegalArgumentException("Empty array");
        } else {
            double closesVal = tempsVal[0];

            for (int i = 0; i < this.size; i++) {
                if (java.lang.Math.abs(tempValue - tempsVal[i]) <
                        java.lang.Math.abs(tempValue - closesVal)) {
                    closesVal = tempsVal[i];
                } else if (java.lang.Math.abs(tempValue - tempsVal[i]) ==
                        java.lang.Math.abs(tempValue - closesVal)) {
                    if (tempsVal[i]*closesVal < 0) {
                        closesVal = java.lang.Math.abs(closesVal);
                    }
                }
            }
            return closesVal;
        }
    }

    public double[] findTempsLessThen(double tempValue) {
        if (this.size  == 0) {
            throw new IllegalArgumentException("Empty array");
        } else {
            double[] results = new double[this.size];
            int curIndx = 0;

            for (int i = 0; i < this.size; i++) {
                if (tempsVal[i] < tempValue) {
                    results[curIndx++] = tempsVal[i];
                }
            }

            double[] return_res = new double[curIndx];
            for (int i = 0; i < curIndx; i++) {
                return_res[i] = results[i];
            }
            return return_res;
        }
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (this.size == 0) {
            throw new IllegalArgumentException("Empty array");
        } else {
            double[] results = new double[this.size];
            int curIndx = 0;

            for (int i = 0; i < this.size; i++) {
                if (tempsVal[i] > tempValue) {
                    results[curIndx++] = tempsVal[i];
                }
            }

            double[] return_res = new double[curIndx];
            for (int i = 0; i < curIndx; i++) {
                return_res[i] = results[i];
            }
            return return_res;
        }
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(this.average(), this.deviation(),
                this.min(), this.max());
    }

    public int addTemps(double... temps) {
        if (tempsVal.length < (this.size + temps.length)) {
            int newSize;
            if (tempsVal.length > 0) {
                newSize = tempsVal.length;
            } else {
                newSize = 1;
            }

            while (newSize < (tempsVal.length + temps.length)) {
                newSize *= 2;
            }


            double[] newArray = new double[newSize];
            for (int i = 0; i < this.size; i++) {
                newArray[i] = tempsVal[i];
            }

            int curr_indx = 0;

            for (int i = this.size; i < (this.size + temps.length); i++) {
                newArray[i] = temps[curr_indx++];
            }

            this.tempsVal = newArray.clone();

        } else {
            for (int i = 0; i < temps.length; i++) {
                tempsVal[i + this.size] = temps[i];
            }
        }

        this.size += temps.length;

        return 0;
    }

    public double[] getTempsVal() {
        return tempsVal;
    }
}
