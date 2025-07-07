
import React from "react";
import styles from "../styles/product-card.module.css";
import { renderStars, normalizeRating } from "../utils/product-utils";

const ProductCard = ({ product }) => {
  const [selectedColor, setSelectedColor] = React.useState("yellow");

  const colorHex = {
    yellow: "#E6CA97",
    white: "#D9D9D9",
    rose: "#E1A4A9",
  };

  const normalizedRating = normalizeRating(product.ratingOutOfFive);

  return (
    <div className={styles.card}>
      <img
        src={product.imageByColor[selectedColor]}
        alt={`${product.name} - ${selectedColor}`}
        className={styles.image}
      />
      <div className={styles.productName}>{product.name || 'Product Title'}</div>
      <div className={styles.productPrice}>
        ${Number(product.priceText).toFixed(2)} USD
      </div>
      <div className={styles.colors}>
        {Object.keys(colorHex).map((color) => (
          <button
            key={color}
            className={`${styles.colorCircle} ${styles[color]} ${
              selectedColor === color ? styles.active : ''
            }`}
            onClick={() => setSelectedColor(color)}
            aria-label={`Select ${color} color`}
          />
        ))}
      </div>
      <div className={styles.colorLabel}>
        {selectedColor === 'yellow' && 'Yellow Gold'}
        {selectedColor === 'white' && 'White Gold'}
        {selectedColor === 'rose' && 'Rose Gold'}
      </div>
      <div className={styles.ratingStars}>
        {renderStars(normalizedRating)}
        <span className={styles.ratingValue}>{normalizedRating.toFixed(1)}/5</span>
      </div>
    </div>
  );
};

export default ProductCard;
