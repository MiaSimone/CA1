package dto;
import entities.Members;
/**
 *
 * @author Malthe
 */
public class MembersDTO {
    private String name;
    private int studentId;
    private String favoriteColor;
    
    
    public MembersDTO(Members members) {
        this.name = members.getName();
        this.studentId = members.getStudentId();
        this.favoriteColor = members.getFavoriteColor();
    }
    
    @Override
    public String toString() {
        return "MembersDTO{" + "name=" + name + ", studentId=" + studentId + ", favoriteColor=" + favoriteColor + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFavoriteColor() {
        return favoriteColor;
    }

    public void setFavoriteColor(String favoriteColor) {
        this.favoriteColor = favoriteColor;
    }
    
    
}
