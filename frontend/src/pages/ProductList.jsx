import { useEffect, useState } from 'react';
import { fetchProducts } from '../services/product-api';
import ProductCarousel from '../components/ProductCarousel';
import styles from '../styles/product-list.module.css';


const ProductList = () => {
  const [products, setProducts] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchProducts()
      .then(setProducts)
      .catch(() => setError('Sorry, there was a problem fetching product data from the server. Please try again later.'));
  }, []);

  if (error) {
    return (
      <div className={styles.container}>
        <h2 className={styles.title}>Product List</h2>
        <div style={{ color: 'red', textAlign: 'center', marginTop: 32 }}>
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
