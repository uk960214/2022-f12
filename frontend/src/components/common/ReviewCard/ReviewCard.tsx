import Rating from '../Rating/Rating';
import UserNameTag from '../UserNameTag/UserNameTag';

import * as S from './ReviewCard.style';

type Props = {
  profileImage: string;
  username: string;
  rating: number;
  content: string;
};

function ReviewCard({ profileImage, username, rating, content }: Props) {
  return (
    <S.Container>
      <S.Wrapper>
        <UserNameTag profileImage={profileImage} username={username} />
        <Rating rating={rating} />
      </S.Wrapper>
      <S.Content>{content}</S.Content>
    </S.Container>
  );
}

export default ReviewCard;
