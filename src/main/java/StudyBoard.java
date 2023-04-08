public class StudyBoard {
    private String[] headers;
    private int locationIndex;

    public StudyBoard(String[] headers, int locationIndex) {
        this.headers = headers;
        this.locationIndex = locationIndex;
    }

    public String[] getHeaders() {
        return headers;
    }

    public int getLocationIndex() {
        return locationIndex;
    }
}
