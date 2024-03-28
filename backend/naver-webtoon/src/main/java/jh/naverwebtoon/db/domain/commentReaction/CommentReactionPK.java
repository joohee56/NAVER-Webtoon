package jh.naverwebtoon.db.domain.commentReaction;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import jh.naverwebtoon.db.domain.Comment;
import jh.naverwebtoon.db.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentReactionPK implements Serializable {
    private Member member;
    private Comment comment;
}
