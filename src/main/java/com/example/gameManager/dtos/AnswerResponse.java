package com.example.gameManager.dtos;

import com.example.gameManager.domains.AnswerStatus;

public class AnswerResponse {
    private AnswerStatus status ;
    private int pointsEarned;

    public AnswerStatus getStatus() {
        return status;
    }

    public void setStatus(AnswerStatus status) {
        this.status = status;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }



    public AnswerResponse(AnswerStatus status, int pointsEarned) {
        this.status = status;
        this.pointsEarned = pointsEarned;
    }

}
