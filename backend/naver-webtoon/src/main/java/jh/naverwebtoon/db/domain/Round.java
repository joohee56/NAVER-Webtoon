package jh.naverwebtoon.db.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.dto.request.CreateRoundReq;
import jh.naverwebtoon.util.RoundEntityListener;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@EntityListeners(value = {RoundEntityListener.class})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Round extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="webtoon_id")
    private Webtoon webtoon;

    private Long roundNumber;   //persist 전 자동 생성 (RoundEventListener.java)

    private String roundTitle;

    @Column(length = 300)
    private String authorNote;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private RoundThumbnail roundThumbnail;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL)
    private List<Manuscript> manuscripts = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MergeManuscript mergeManuscript;

    public void setRoundNumber(Long roundNumber) {
        this.roundNumber = roundNumber;
    }

    /**
     * 연관관계 메서드
     */
    public void addManuscript(Manuscript manuscript) {
        this.manuscripts.add(manuscript);
        manuscript.setRound(this);
    }

    public static Round create(CreateRoundReq createRoundReq, Webtoon webtoon, RoundThumbnail roundThumbnail, List<Manuscript> manuscripts, MergeManuscript mergeManuscript) {
        Round round = new Round();
        round.webtoon = webtoon;
        round.roundTitle = createRoundReq.getRoundTitle();
        round.authorNote = createRoundReq.getAuthorNote();
        round.roundThumbnail = roundThumbnail;
        for (Manuscript manuscript : manuscripts) {
            round.addManuscript(manuscript);
        }
        round.mergeManuscript = mergeManuscript;
        return round;
    }
}
