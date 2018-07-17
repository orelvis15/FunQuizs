package gindi.com.funquizs;

public class Problems {
    private String id;
    private String name;
    private String title;
    private String content;
    private String answer;
    private String answerType;
    private String updateAt;

    public Problems(String id, String name, String title, String content, String answer, String answerType, String updateAt) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.answerType = answerType;
        this.updateAt = updateAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
