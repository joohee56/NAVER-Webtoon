package jh.naverwebtoon.db.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(RoundLikePK.class)
public class RoundLike {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "round_id")
    private Round round;

    public static RoundLike create(Member member, Round round) {
        RoundLike roundLike = new RoundLike();
        roundLike.member = member;
        roundLike.round = round;
        return roundLike;
    }
}
