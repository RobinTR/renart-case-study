import styles from "../styles/product-card.module.css";

// Convert value in the range 0-1 to a value in the range 1-5
export function normalizeRating(rating) {
  return 1 + (Number(rating ?? 0) * 4);
}

// Render stars based on the rating value
export function renderStars(rating) {
  const stars = [];
  const fullStars = Math.floor(rating);
  const halfStar = rating - fullStars >= 0.5;

  for (let i = 0; i < fullStars; i++) {
    stars.push(<span key={i} className={styles.star}>★</span>);
  }

  if (halfStar) {
    stars.push(<span key="half" className={styles.star}>☆</span>);
  }

  for (let i = stars.length; i < 5; i++) {
    stars.push(<span key={i + 5} className={styles.starEmpty}>★</span>);
  }

  return stars;
}