public class TeresaResponse {
    Boolean notok;
    String description;

    public TeresaResponse(Boolean notok, String description) {
        this.notok = notok;
        this.description = description;
    }

    public Boolean getNotok() {
        return notok;
    }

    public void setOk(Boolean notok) {
        this.notok = notok;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
