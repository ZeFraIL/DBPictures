package zeev.fraiman.dbpictures;

public class Info {
    private String infoComment;
    private byte[] infoImage;

    public Info(String infoComment, byte[] infoImage) {
        this.infoComment = infoComment;
        this.infoImage = infoImage;
    }

    public String getInfoComment() {
        return infoComment;
    }

    public void setInfoComment(String infoComment) {
        this.infoComment = infoComment;
    }

    public byte[] getInfoImage() {
        return infoImage;
    }

    public void setInfoImage(byte[] infoImage) {
        this.infoImage = infoImage;
    }
}
