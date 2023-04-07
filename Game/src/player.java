public class player {
    private String name;
    private int[][] easy;
    private int[][] hard;
    private int easyPoints;
    private int hardPoints;

    public player(String name, int[][] easy, int[][] hard,int easyPoints,int hardPoints) {
        this.name = name;
        this.easy = easy;
        this.hard = hard;
        this.easyPoints = easyPoints;
        this.hardPoints = hardPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[][] getEasy() {
        return easy;
    }

    public void setEasy(int[][] easy) {
        this.easy = easy;
    }

    public int[][] getHard() {
        return hard;
    }

    public void setHard(int[][] hard) {
        this.hard = hard;
    }

    public int getEasyPoints() {
        return easyPoints;
    }

    public void setEasyPoints(int easyPoints) {
        this.easyPoints = easyPoints;
    }

    public int getHardPoints() {
        return hardPoints;
    }

    public void setHardPoints(int hardPoints) {
        this.hardPoints = hardPoints;
    }
}
