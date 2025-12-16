import React, { useState, useCallback } from 'react';
import { ScrollView, Image, View, Text, StyleSheet, FlatList, TouchableOpacity } from 'react-native';

import { useNavigation, useFocusEffect } from '@react-navigation/native';
import AsyncStorage from '@react-native-async-storage/async-storage';

export default function HomeScreen() {
  const navigation = useNavigation();

  const goToDetail = (title, store, description, image) => {
    navigation.navigate('ServiceDetail', {
      title,
      store,
      description,
      image,
    });
  };

  const [bookings, setBookings] = useState([]);

  useFocusEffect(
    useCallback(() => {
      const loadBookings = async () => {
        try {
          const stored = await AsyncStorage.getItem('bookings');
          if (stored) {
            const allBookings = JSON.parse(stored);
            const activeBookings = allBookings.filter(b => !b.completed);
            setBookings(activeBookings);
          } else {
            setBookings([]);
          }
        } catch (e) {
          console.error('Erro ao carregar agendamentos', e);
        }
      };

      loadBookings();
    }, [])
  );

  const renderBooking = ({ item, index }) => (
    <View style={styles.bookingCardRow}>
      <TouchableOpacity
        style={styles.bookingCard}
        onPress={() =>
          navigation.navigate('BookingDetailsScreen', { booking: item })
        }
      >
        <Text style={styles.bookingTitle}>{item.title} - {item.store}</Text>
        <Text style={styles.bookingDate}>
          {new Date(item.date).toLocaleDateString()} às{' '}h
          {new Date(item.date).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}
        </Text>
      </TouchableOpacity>
    </View>
  );

  return (
    <View style={styles.container}>
      <Image source={require('../assets/logo_transparente.png')} style={styles.logo} />
      <ScrollView contentContainerStyle={styles.scrollContent}>

        {/* Scheduled services */}
        <View style={styles.section}>
          <View style={styles.sectionHeader}>
            <Image source={require('../assets/Calendar.png')} style={styles.icon} />
            <Text style={styles.sectionTitle}>Scheduled Services</Text>
            <Text style={styles.dots}>•••</Text>
          </View>
          <View style={styles.sectionContent}>
            {bookings.length === 0 ? (
              <View style={[styles.sectionContent, { alignItems: 'center' }]}>
                <>
                  <Text style={styles.noServices}>You have no upcoming services.</Text>
                  <View style={styles.searchContainer}>
                    <Image source={require('../assets/Search_grey.png')} style={styles.icon} />
                    <Text style={styles.searchText}>Search service</Text>
                  </View>
                </>
              </View>
            ) : (
              <View style={{ alignItems: 'center' }}>
                <FlatList
                  data={bookings}
                  keyExtractor={(_, i) => i.toString()}
                  renderItem={renderBooking}
                  contentContainerStyle={{ width: '100%' }}
                />
              </View>
            )}
          </View>

          <Image source={require('../assets/chevron_down.png')} style={styles.chevronIcon} />
        </View>

        {/* Offers and deals */}
        <View style={styles.section}>
          <View style={styles.sectionHeader}>
            <Image source={require('../assets/Tag.png')} style={styles.icon} />
            <Text style={styles.sectionTitle}>Offers & Deals</Text>
            <Text style={styles.dots}>•••</Text>
          </View>
          <View style={styles.sectionContent}>
            <View style={styles.recommendedContainer}>
              {/* Oferta 1 */}
              <TouchableOpacity style={styles.recommendedCard} onPress={() => navigation.navigate('ProviderProfile')}>
                <Image source={require('../assets/store_logos/Northside.png')} style={styles.offerLogo} />
                <View style={styles.recommendedTextContainer}>
                  <Text style={styles.recommendedText}>20% OFF on inspections until April 20!</Text>
                  <Text style={styles.distanceText}>Northside Auto Repair</Text>
                </View>
              </TouchableOpacity>

              {/* Oferta 2 */}
              <View style={styles.recommendedCard}>
                <Image source={require('../assets/store_logos/RiverStone.png')} style={styles.offerLogo} />
                <View style={styles.recommendedTextContainer}>
                  <Text style={styles.recommendedText}>15% OFF on oil changes until April 25!</Text>
                  <Text style={styles.distanceText}>Riverstone Automotive</Text>
                </View>
              </View>
            </View>
          </View>
          <Image source={require('../assets/chevron_down.png')} style={styles.chevronIcon} />
        </View>


        {/* Recommended near you */}
        <View style={styles.section}>
          <View style={styles.sectionHeader}>
            <Image source={require('../assets/Calendar.png')} style={styles.icon} />
            <Text style={styles.sectionTitle}>Recommended Near You</Text>
            <Text style={styles.dots}>•••</Text>
          </View>
          <View style={styles.sectionContent}>
            <View style={styles.recommendedContainer}>
              <TouchableOpacity style={styles.recommendedCard} onPress={() => navigation.navigate('ProviderProfile')}>
                <Image source={require('../assets/store_logos/thompson.png')} style={styles.offerLogo} />
                <View style={styles.recommendedTextContainer}>
                  <Text style={styles.recommendedText}>Thompson Car Service</Text>
                  <View style={styles.recommendedTextLine}>
                    <Image source={require('../assets/map_pin.png')} style={styles.icon} />
                    <Text style={styles.distanceText}>2.1 km away</Text>
                  </View>
                  <View style={styles.recommendedTextLine}>
                    <Image source={require('../assets/star.png')} style={styles.icon} />
                    <Text style={styles.distanceText}>4.5</Text>
                  </View>
                </View>
              </TouchableOpacity>

              <View style={styles.recommendedCard}>
                <Image source={require('../assets/store_logos/RiverStone.png')} style={styles.offerLogo} />
                <View style={styles.recommendedTextContainer}>
                  <Text style={styles.recommendedText}>Riverstone Automotive</Text>
                  <View style={styles.recommendedTextLine}>
                    <Image source={require('../assets/map_pin.png')} style={styles.icon} />
                    <Text style={styles.distanceText}>1.6 km away</Text>
                  </View>
                  <View style={styles.recommendedTextLine}>
                    <Image source={require('../assets/star.png')} style={styles.icon} />
                    <Text style={styles.distanceText}>4.2</Text>
                  </View>
                </View>
              </View>
            </View>
          </View>
          <Image source={require('../assets/chevron_down.png')} style={styles.chevronIcon} />
        </View>
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#F5F5F5',
    width: '100%',
  },
  logo: {
    width: 180,
    height: 100,
    resizeMode: 'contain',
    marginTop: 30,
    marginBottom: 20,
    alignSelf: 'center',
  },
  scrollContent: {
    flexGrow: 1,
    paddingBottom: 20,
    width: '100%',
  },
  section: {
    width: '100%',
  },
  sectionHeader: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    padding: 30,
  },
  sectionContent: {
    backgroundColor: '#fff',
    width: '100%',
    justifyContent: 'center',
    alignItems: 'flex-start',
    padding: 20,
    marginTop: 5,
  },
  servicesContainer: {
    backgroundColor: '#fff',
    width: '100%',
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20,
  },
  offersContainer: {
    justifyContent: 'space-between',
    width: '100%',
  },
  offerCard: {
    marginBottom: 5,
    borderBottomWidth: 1,
    borderBottomColor: '#ccc',
    flexDirection: 'row',
    alignItems: 'center',
  },
  offerTextContainer: {
    marginLeft: 10,
    flex: 1,
  },
  offerLogo: {
    borderRadius: 25,
  },
  chevronIcon: {
    width: 20,
    height: 20,
    marginTop: 10,
    alignSelf: 'center',
  },
  searchContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    marginTop: 10,
  },
  searchText: {
    marginLeft: 5,
    color: '#888',
  },
  recommendedContainer: {
    width: '100%',
  },
  recommendedCard: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 5,
    width: '100%',
    paddingVertical: 10,
    borderBottomWidth: 1,
    borderColor: '#eee',
  },

  recommendedTextContainer: {
    flex: 1,
    flexDirection: 'column',
    justifyContent: 'space-between',
    marginLeft: 10,
    paddingBottom: 10,
    borderBottomWidth: 1,
    borderBottomColor: '#ccc',
  },
  recommendedTextLine: {
    flexDirection: 'row',
    marginTop: 5,
    marginRight: 5,
  },
  recommendedText: {
    fontWeight: 'bold',
  },
  distanceText: {
    marginRight: 10,
  },
  bookingCardRow: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    backgroundColor: '#fff',
    padding: 5,
    marginBottom: 5,
    borderRadius: 10,
    shadowColor: '#000',
    shadowOpacity: 0.05,
    shadowRadius: 4,
    elevation: 2,
  },
  bookingCard: {
    flex: 1,
  },
  bookingTitle: {
    fontWeight: 'bold',
  },
  bookingDate: {
    color: '#666',
  },
  checkButton: {
    marginLeft: 10,
  },
});
