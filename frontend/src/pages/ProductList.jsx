import { useEffect, useState } from "react";
import { fetchProducts } from "../services/productApi";
import ProductCarousel from "../components/ProductCarousel";
import styles from "../styles/ProductList.module.css";
import spinnerStyles from "../styles/Spinner.module.css";

const ProductList = () => {
  const [products, setProducts] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    fetchProducts()
      .then(setProducts)
      .catch(() =>
        setError(
          "Sorry, there was a problem fetching product data from the server. Please try again later."
        )
      )
      .finally(() => setLoading(false));
  }, []);

  if (loading) {
    return (
      <div className={styles.container}>
        <h2 className={styles.title}>Product List</h2>
        <div className={spinnerStyles.spinner}>
          <div className={spinnerStyles["spinner-circle"]} />
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className={styles.container}>
        <h2 className={styles.title}>Product List</h2>
        <div style={{ color: "red", textAlign: "center", marginTop: 32 }}>
          {error}
        </div>
      </div>
    );
  }

  return (
    <div className={styles.container}>
      <h2 className={styles.title}>Product List</h2>
      <ProductCarousel products={products} />
    </div>
  );
};

export default ProductList;
