package com.corejsf;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("game")
@ConversationScoped
public class Game implements Serializable {
	private Integer numberOfTries=0;
	private Integer answer;
	private Integer lowerBound=1;
	private Integer upperBound=100;
	private Integer guess=null;
	private String message;
	
    @Inject
    private Conversation conversation;
	
	@Inject
	private RandomNumberGenerator randomNumberGenerator;
	
    private int randomNumber;

    public void startGame() {
        conversation.begin();
        randomNumber = randomNumberGenerator.getRandomNumber();
    }
    
    @PostConstruct
    public void init() {
        startGame();
    }
	
	public Integer getNumberOfTries() {
	    this.numberOfTries++;
		return numberOfTries;
	}
	public void setNumberOfTries(Integer numberOfTries) {
		this.numberOfTries = numberOfTries;
	}
	public Integer getAnswer() {
		return answer;
	}
	public void setAnswer(Integer answer) {
		this.answer = answer;
	}
	public Integer getLowerBound() {
		return lowerBound;
	}
	public void setLowerBound(Integer lowerBound) {
		this.lowerBound = lowerBound;
	}
	public Integer getUpperBound() {
		return upperBound;
	}
	public void setUpperBound(Integer upperBound) {
		this.upperBound = upperBound;
	}
	public Integer getGuess() {
		return guess;
	}
	public void setGuess(Integer guess) {
		if(guess<1 || guess>100 || guess>upperBound || guess<lowerBound || guess==null) {
			setErrorMesage("wrong range number!!!");
			return;
		} 
		this.guess = guess;
	}
	public void setErrorMesage(String message) {
		this.message = message;
	}
	public String getErrorMessage() {
		return message;
	}

	public String answerAction() {
	    getNumberOfTries();
	    if(randomNumber > getGuess()) {
	        setLowerBound(getGuess()+1);
	        return "lowScore";
	    } else if(randomNumber < getGuess()) {
	        setUpperBound(getGuess()-1);
	        return "highScore";
	    } else {
	        if (!conversation.isTransient()) { 
	            conversation.end();
	        }
	        return "correctScore";
	    }
	}
	
    public String resetGame() {
        setNumberOfTries(-1);
        setLowerBound(1);
        setUpperBound(100);
        randomNumber = randomNumberGenerator.getRandomNumber();
        return "gameStart";
    }
}
