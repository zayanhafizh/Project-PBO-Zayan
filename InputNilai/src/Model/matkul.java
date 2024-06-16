package Model;

public class matkul {
    public static int instanceCount = 0; // Konvensi penamaan
    private int id;
    private String namaMatkul;
    private double nilaiUTS;
    private double nilaiUAS;
    private double nilaiTugas;
    private double nilaiAkhir;
    private int bobotSks=18;

    private char grade;

    // Getter dan Setter
    public double getNilaiAkhir() {
        return nilaiAkhir;
    }

    public void setNilaiAkhir() {
        double nilaiAkhir = (nilaiTugas * 0.30) + (nilaiUTS * 0.30) + (nilaiUAS * 0.40);
        this.nilaiAkhir = nilaiAkhir;
    }

    public String getNamaMatkul() {
        return namaMatkul;
    }

    public void setNamaMatkul(String namaMatkul) {
        this.namaMatkul = namaMatkul;
    }

    public double getNilaiUTS() {
        return nilaiUTS;
    }

    public void setNilaiUTS(double nilaiUTS) { // Gunakan double untuk konsistensi
        this.nilaiUTS = nilaiUTS;
    }

    public double getNilaiUAS() {
        return nilaiUAS;
    }

    public void setNilaiUAS(double nilaiUAS) { // Gunakan double untuk konsistensi
        this.nilaiUAS = nilaiUAS;
    }

    public double getNilaiTugas() {
        return nilaiTugas;
    }

    public void setNilaiTugas(double nilaiTugas) { // Gunakan double untuk konsistensi
        this.nilaiTugas = nilaiTugas;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade() {
        if (nilaiAkhir < 60) {
            this.grade = 'D';
        } else if (nilaiAkhir >= 60 && nilaiAkhir < 70) {
            this.grade = 'C';
        } else if (nilaiAkhir >= 70 && nilaiAkhir < 80) {
            this.grade = 'B';
        } else {
            this.grade = 'A';
        }
    }
    public int getBobotSks() {
        return bobotSks;
    }
}
