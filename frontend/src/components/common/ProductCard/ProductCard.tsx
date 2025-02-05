import Rating from '../Rating/Rating';
import * as S from './ProductCard.style';

type Props = {
  productImage?: string;
  name: string;
  rating: number;
};

function ProductCard({ productImage, name, rating }: Props) {
  return (
    <S.Container>
      <S.Image src={productImage} />
      <S.Name>{name}</S.Name>
      <Rating rating={rating} />
    </S.Container>
  );
}

export default ProductCard;
