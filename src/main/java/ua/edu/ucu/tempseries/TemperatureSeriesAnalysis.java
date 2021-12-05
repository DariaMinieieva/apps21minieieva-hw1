package ua.edu.ucu.tempseries;
import static java.lang.Math.pow;
import static java.lang.Math.abs;

public class TemperatureSeriesAnalysis {
    private double[] temps_val = {};
    private int size = 0;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temps_val = temperatureSeries.clone();
        this.size = this.temps_val.length;
    }

    public double average() {
        if (this.size  == 0) {
            throw new IllegalArgumentException("Empty array");
        } else {
            double sum_of_elems = 0;
            for (int i = 0; i < this.size ; i++) {
                sum_of_elems += temps_val[i];
            }

            return sum_of_elems/this.size ;
        }
    }

    public double deviation() {
        if (this.size  == 0) {
            throw new IllegalArgumentException("Empty array");
        } else {
            double mean = this.average();
            double tempr_sum = 0;

            for(int i = 0; i < this.size ; i++) {
                tempr_sum += pow(temps_val[i] - mean, 2);
            }

            if (tempr_sum == 0) {
                return 0.0;
            } else {
                return pow(tempr_sum/(this.size  - 1), 0.5);
            }
        }
    }

    public double min() {
        if (this.size  == 0) {
            throw new IllegalArgumentException("Empty array");
        } else {
            double min_val = temps_val[0];
            for (int i = 1; i < this.size; i++) {
                if (min_val > temps_val[i]) {
                    min_val = temps_val[i];
                }
            }

            return min_val;
        }
    }

    public double max() {
        if (this.size == 0) {
            throw new IllegalArgumentException("Empty array");
        } else {
            double max_val = temps_val[0];
            for (int i = 1; i < this.size; i++) {
                if (max_val < temps_val[i]) {
                    max_val = temps_val[i];
                }
            }

            return max_val;
        }
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (this.size  == 0) {
            throw new IllegalArgumentException("Empty array");
        } else {
            double closest_val = temps_val[0];

            for (int i = 0; i < this.size ; i++) {
                if (abs(tempValue - temps_val[i]) < abs(tempValue - closest_val)) {
                    closest_val = temps_val[i];
                } else if (abs(tempValue - temps_val[i]) == abs(tempValue - closest_val)) {
                    if (temps_val[i]*closest_val < 0) {
                        closest_val = abs(closest_val);
                    }
                }
            }
            return closest_val;
        }
    }

    public double[] findTempsLessThen(double tempValue) {
        if (this.size  == 0) {
            throw new IllegalArgumentException("Empty array");
        } else {
            double[] results = new double[this.size];
            int cur_indx = 0;

            for (int i = 0; i < this.size ; i++) {
                if (temps_val[i] < tempValue) {
                    results[cur_indx++] = temps_val[i];
                }
            }

            double[] return_res = new double[cur_indx];
            for (int i = 0; i < cur_indx; i++) {
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
            int cur_indx = 0;

            for (int i = 0; i < this.size; i++) {
                if (temps_val[i] > tempValue) {
                    results[cur_indx++] = temps_val[i];
                }
            }

            double[] return_res = new double[cur_indx];
            for (int i = 0; i < cur_indx; i++) {
                return_res[i] = results[i];
            }
            return return_res;
        }
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(this.average(), this.deviation(), this.min(), this.max());
    }

    public int addTemps(double... temps) {
        if (temps_val.length < (this.size + temps.length)) {
            int new_size;
            if (temps_val.length > 0) {
                new_size = temps_val.length;
            } else {
                new_size = 1;
            }

            while (new_size < (temps_val.length + temps.length)) {
                new_size*=2;
            }


            double[] new_array = new double[new_size];
            for (int i = 0; i < this.size; i++) {
                new_array[i] = temps_val[i];
            }

            int curr_indx = 0;

            for (int i = this.size; i < (this.size + temps.length); i++) {
                new_array[i] = temps[curr_indx++];
            }

            this.temps_val = new_array.clone();

        } else {
            for (int i = 0; i < temps.length; i++) {
                temps_val[i + this.size] = temps[i];
            }
        }

        this.size += temps.length;

        return 0;
    }

    public double[] getTemps_val() {
        return temps_val;
    }
}
