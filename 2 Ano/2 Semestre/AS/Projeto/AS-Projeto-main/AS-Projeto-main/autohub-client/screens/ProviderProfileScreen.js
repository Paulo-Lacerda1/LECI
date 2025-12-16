// screens/ProfileScreen.js
import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet, Image, ScrollView, TouchableOpacity, Alert } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';

export default function ProviderProfileScreen({ navigation }) {
  const [reviews, setReviews] = useState([]);
  const [visibleCount, setVisibleCount] = useState(3);

  const [averageRating, setAverageRating] = useState(0);
  const [reviewsCount, setReviewsCount] = useState(0);

  useEffect(() => {
    if (reviews.length > 0) {
      const total = reviews.reduce((sum, r) => sum + r.rating, 0);
      const avg = total / reviews.length;
      setAverageRating(avg.toFixed(1));
      setReviewsCount(reviews.length);
    } else {
      setAverageRating(0);
      setReviewsCount(0);
    }
  }, [reviews]);


  const provider = {
    name: 'Thompson Car Service',
    address: 'Avenida Exemplo, 123',
    openHours: 'Mon-Fri 08:00–18:00 | Sat 10:00–14:00',
  };

  const services = [
    {
      id: 1,
      name: 'Oil Change',
      price: 50,
      description: 'Complete engine oil replacement',
      image: require('../assets/profile/oil_change.png'),
      extras: [
        { label: 'Interior Cleaning', price: 10 },
        { label: 'Wax Finish', price: 5 },
        { label: 'Wheel Detailing', price: 6 },
      ],
    },
    {
      id: 2,
      name: 'Premium Exterior Clean',
      price: 70,
      description: 'A detailed hand wash',
      image: require('../assets/profile/exterior_clean.png'),
      extras: [
        { label: 'Interior Cleaning', price: 10 },
        { label: 'Wax Finish', price: 5 },
        { label: 'Wheel Detailing', price: 6 },
      ],
    },
  ];

  useEffect(() => {
    const fetchReviews = async () => {
      try {
        const stored = await AsyncStorage.getItem('bookings');
        const all = stored ? JSON.parse(stored) : [];
        const filtered = all
          .filter(b => b.completed && b.review && b.store === provider.name)
          .map(b => ({
            ...b.review,
            id: b.review.id || b.date + b.title,
          }))
          .sort((a, b) => b.id - a.id);

        setReviews(filtered);
      } catch (e) {
        console.error('Failed to load reviews', e);
        Alert.alert('Error', 'Could not load reviews.');
      }
    };

    const unsubscribe = navigation.addListener('focus', fetchReviews);
    return unsubscribe;
  }, [navigation]);

  return (
    <ScrollView style={styles.container}>
      {/* Header */}
      <Image source={require('../assets/logo_transparente.png')} style={styles.logo} />

      <TouchableOpacity onPress={() => navigation.goBack()} style={styles.navIcon}>
        <Image source={require('../assets/arrowleft.png')} style={styles.backIcon} />
      </TouchableOpacity>

      {/* Oficina Info */}
      <View style={styles.headerSection}>
        <Image source={require('../assets/store_logos/thompson.png')} style={styles.storeLogo} />
        <View style={styles.headerText}>
          <Text style={styles.storeName}>{provider.name}</Text>
          <Text style={styles.rating}>⭐ {averageRating} ({reviewsCount} reviews)</Text>
          <Text style={styles.details}>Address: {provider.address}</Text>
          <Text style={styles.details}>Open: {provider.openHours}</Text>
        </View>
      </View>

      {/* Popular */}
      <Text style={styles.sectionTitle}>Most popular</Text>
      <TouchableOpacity
        onPress={() => navigation.navigate('BookingScreen', {
          service: services[1],
          store: provider.name,
        })}
      >
        <Image source={services[1].image} style={styles.popularImage} />
        <Text style={styles.serviceName}>{services[1].name}</Text>
      </TouchableOpacity>

      {/* Services */}
      <Text style={styles.sectionTitle}>Services</Text>
      <ScrollView horizontal showsHorizontalScrollIndicator={false} style={styles.servicesScroll}>
        {services.map(service => (
          <TouchableOpacity
            key={service.id}
            style={styles.serviceCard}
            onPress={() =>
              navigation.navigate('BookingScreen', {
                service,
                store: 'Thompson Car Service',
              })
            }
          >
            <Image source={service.image} style={styles.serviceImage} />
            <Text style={styles.serviceName}>{service.name}</Text>
            <Text style={styles.servicePrice}>{service.price}€</Text>
            <Text style={styles.serviceDescription}>{service.description}</Text>
          </TouchableOpacity>
        ))}
      </ScrollView>

      {/* Reviews */}
      <Text style={styles.sectionTitle}>Latest reviews</Text>
      {reviews.length === 0 ? (
        <Text style={{ fontStyle: 'italic', color: '#777', marginBottom: 20 }}>No reviews yet.</Text>
      ) : (
        reviews.slice(0, visibleCount).map(review => (
          <View key={review.id} style={styles.reviewCard}>
            <Text style={styles.reviewRating}>⭐ {review.rating}</Text>
            <Text style={styles.reviewTitle}>{review.title || 'Review'}</Text>
            <Text style={styles.reviewText}>{review.text}</Text>
            <Text style={styles.reviewMeta}>{review.user} · {review.date}</Text>
          </View>
        ))
      )}

      {visibleCount < reviews.length && (
        <TouchableOpacity onPress={() => setVisibleCount(prev => prev + 3)}>
          <Text style={styles.seeMore}>See more</Text>
        </TouchableOpacity>
      )}
    </ScrollView>
  );
}


const styles = StyleSheet.create({
  container: {
    backgroundColor: '#F5F5F5',
    flex: 1,
    paddingHorizontal: 20,
  },
  logo: {
    width: 180,
    height: 80,
    resizeMode: 'contain',
    alignSelf: 'center',
    marginVertical: 10,
  },
  headerSection: {
    flexDirection: 'row',
    marginBottom: 20,
  },
  storeLogo: {
    width: 60,
    height: 60,
    resizeMode: 'contain',
    marginRight: 10,
  },
  headerText: {
    flex: 1,
    justifyContent: 'center',
  },
  storeName: {
    fontSize: 18,
    fontWeight: 'bold',
  },
  rating: {
    color: '#333',
    marginVertical: 2,
  },
  details: {
    fontSize: 13,
    color: '#666',
  },
  sectionTitle: {
    fontSize: 16,
    fontWeight: 'bold',
    marginTop: 20,
    marginBottom: 10,
  },
  popularImage: {
    width: '100%',
    height: 180,
    borderRadius: 8,
  },
  serviceName: {
    fontWeight: 'bold',
    fontSize: 14,
    marginTop: 5,
  },
  servicesScroll: {
    marginBottom: 20,
  },
  serviceCard: {
    width: 140,
    marginRight: 15,
  },
  serviceImage: {
    width: '100%',
    height: 100,
    borderRadius: 6,
  },
  servicePrice: {
    fontWeight: 'bold',
    marginTop: 2,
  },
  serviceDescription: {
    fontSize: 12,
    color: '#555',
  },
  reviewCard: {
    backgroundColor: '#fff',
    padding: 15,
    borderRadius: 8,
    marginBottom: 15,
  },
  reviewRating: {
    fontWeight: 'bold',
    fontSize: 14,
  },
  reviewTitle: {
    fontSize: 15,
    fontWeight: '600',
    marginVertical: 4,
  },
  reviewText: {
    fontSize: 13,
    color: '#333',
  },
  reviewMeta: {
    marginTop: 6,
    fontSize: 12,
    color: '#666',
  },
  seeMore: {
    textAlign: 'center',
    color: '#000',
    textDecorationLine: 'underline',
    marginBottom: 30,
  },
  navIcon: {
    marginBottom: 20,
  }
});
