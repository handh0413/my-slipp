package net.slipp.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Question extends AbstractEntity {
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	@JsonProperty
	private User writer;
	
	@OneToMany(mappedBy = "question")	// Answer 클래스의 question 필드와 mapping 된다.
	@OrderBy("id ASC")					// Answer 객체의 id 컬럼을 기준으로 정렬한다.
	private List<Answer> answers;
	
	@JsonProperty
	private String title;
	
	@Lob
	@JsonProperty
	private String contents;
	
	@JsonProperty
	private int countOfAnswer = 0;
	
	public Question(User writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}
	
	public Question() {
		super();
	}

	public User getWriter() {
		return writer;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Integer getAnswersCount() {
		return answers.size();
	}

	@Override
	public String toString() {
		return "Question [" + super.toString() + ", writer=" + writer + ", answers=" + answers + ", title=" + title + ", contents=" + contents
				+ ", countOfAnswer=" + countOfAnswer + "]";
	}

	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}

	public boolean isSameWriter(User sessionedUser) {
		return this.writer.equals(sessionedUser);
	}

	public void addAnswer() {
		this.countOfAnswer += 1;
	}
	
	public void deleteAnswer() {
		this.countOfAnswer -= 1;
	}
}
