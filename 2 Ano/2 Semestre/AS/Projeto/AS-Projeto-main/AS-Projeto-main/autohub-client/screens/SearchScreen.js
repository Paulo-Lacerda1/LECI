// screens/SearchScreen.js
import React , {useState} from 'react';
import { View, Text, StyleSheet, TextInput, Image, ScrollView, TouchableOpacity } from 'react-native';

export default function SearchScreen({ navigation }) {
  const [searchTerm, setSearchTerm] = useState('');
  const [activeFilters, setActiveFilters] = useState(['Services', 'Workshops']);
  const [showFilters, setShowFilters] = useState(false);

  const toggleFilter = (filter) => {
    setActiveFilters(prev =>
      prev.includes(filter) ? prev.filter(f => f !== filter) : [...prev, filter]
    );
  };

  // mock data
  const allServices = ["Car Wash", "Interior Cleaning", "Full Wash", "Waxing"];
  const allWorkshops = [
    { name: "Thompson Car Service", distance: "2.1 km", rating: 4.5, logo: require('../assets/store_logos/thompson.png') },
    { name: "Prime Car Service Center", distance: "4.3 km", rating: 4.1, logo: require('../assets/store_logos/prime.png') },
    { name: "Urban Car Services", distance: "6.8 km", rating: 4.6, logo: require('../assets/store_logos/urban.png') }
  ];

  const filteredServices = allServices.filter(service =>
    activeFilters.includes('Services') &&
    service.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const filteredWorkshops = allWorkshops.filter(workshop =>
    activeFilters.includes('Workshops') &&
    workshop.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <ScrollView style={styles.container} contentContainerStyle={styles.scrollContent}>
      <Image source={require('../assets/logo_transparente.png')} style={styles.logo} />

      {/* Campo de busca */}
      <View style={styles.searchBox}>
        <TextInput
          placeholder="What are you looking for?"
          style={styles.searchInput}
          value={searchTerm}
          onChangeText={text => setSearchTerm(text)}
        />
        <TouchableOpacity onPress={() => setShowFilters(prev => !prev)}>
          <Image source={require('../assets/filter.png')} style={styles.filterIcon} />
        </TouchableOpacity>
      </View>

      {/* Filtros visíveis apenas com pesquisa */}
      {searchTerm !== '' && showFilters && (
        <View style={{ flexDirection: 'row', flexWrap: 'wrap', gap: 8, marginBottom: 10 }}>
          {['Services', 'Workshops', 'Near me'].map(filter => (
            <TouchableOpacity
              key={filter}
              onPress={() => toggleFilter(filter)}
              style={{
                backgroundColor: activeFilters.includes(filter) ? '#ddd' : '#fff',
                borderColor: '#ccc',
                borderWidth: 1,
                borderRadius: 20,
                paddingHorizontal: 12,
                paddingVertical: 6,
                marginRight: 8,
                marginTop: 5,
              }}
            >
              <Text>{filter}</Text>
            </TouchableOpacity>
          ))}
        </View>
      )}

      {searchTerm === '' ? (
        <>
          {/* Recently Searched */}
          <View style={styles.section}>
            <Text style={styles.sectionTitle}>Recently Searched</Text>
            <Text style={styles.secondaryText}>
              You haven't searched for anything yet.{"\n"}Start exploring services or workshops now!
            </Text>
          </View>

          {/* Popular Services */}
          <View style={styles.section}>
            <Text style={styles.sectionTitle}>Popular Services</Text>
            <View style={styles.grid}>
              {allServices.map((service, index) => (
                <Text key={index} style={styles.gridItem}>{service}</Text>
              ))}
            </View>
          </View>

          {/* Recommended for you */}
          <View style={styles.section}>
            <Text style={styles.sectionTitle}>Recommended for you</Text>

            <Text style={styles.subSectionTitle}>Services</Text>
            <View style={styles.row}>
              <Text style={styles.serviceItem}>Wheel Alignment</Text>
              <Text style={styles.serviceItem}>Battery Check</Text>
            </View>

            <Text style={styles.subSectionTitle}>Workshops</Text>
            <View style={styles.recommendCard}>
              <TouchableOpacity style={styles.recommendCard} onPress={() => navigation.navigate('ProviderProfile')}>
                <Image source={require('../assets/store_logos/thompson.png')} style={styles.storeLogo} />
                <Text style={styles.recommendText}>Thompson Car Service</Text>
              </TouchableOpacity>
            </View>
            <View style={styles.recommendCard}>
              <Image source={require('../assets/store_logos/RiverStone.png')} style={styles.storeLogo} />
              <Text style={styles.recommendText}>Riverstone Automotive</Text>
            </View>
          </View>
        </>
      ) : (
        <>
          {/* Searching Results */}

          {filteredServices.length > 0 && (
            <View style={styles.section}>
              <Text style={styles.sectionTitle}>Services</Text>
              {filteredServices.map((service, index) => (
                <Text key={index} style={styles.gridItem}>{service}</Text>
              ))}
            </View>
          )}

          {filteredWorkshops.length > 0 && (
            <View style={styles.section}>
              <Text style={styles.sectionTitle}>Workshops</Text>
              {filteredWorkshops.map((shop, index) => (
                <TouchableOpacity
                  key={index}
                  style={styles.recommendCard}
                  onPress={() => {
                    if (shop.name === 'Thompson Car Service') {
                      navigation.navigate('ProviderProfile');
                    }
                  }}
                >
                  <Image source={shop.logo} style={styles.storeLogo} />
                  <View>
                    <Text style={styles.recommendText}>{shop.name}</Text>
                    <Text style={{ fontSize: 12, color: '#666' }}>{shop.distance} away</Text>
                    <Text style={{ fontSize: 12 }}>⭐ {shop.rating}</Text>
                  </View>
                </TouchableOpacity>
              ))}
            </View>
          )}

          {filteredServices.length === 0 && filteredWorkshops.length === 0 && (
            <Text style={styles.secondaryText}>No results found.</Text>
          )}
        </>
      )}
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#F5F5F5',
  },
  scrollContent: {
    paddingBottom: 30,
    paddingHorizontal: 20,
  },
  logo: {
    width: 180,
    height: 100,
    resizeMode: 'contain',
    alignSelf: 'center',
    marginTop: 30,
    marginBottom: 20,
  },
  searchBox: {
    flexDirection: 'row',
    backgroundColor: '#fff',
    borderRadius: 8,
    paddingHorizontal: 12,
    paddingVertical: 10,
    alignItems: 'center',
    marginBottom: 20,
  },
  searchInput: {
    flex: 1,
    fontSize: 16,
  },
  filterIcon: {
    width: 20,
    height: 20,
    marginLeft: 10,
  },
  section: {
    backgroundColor: '#fff',
    padding: 16,
    marginBottom: 20,
    borderRadius: 8,
  },
  sectionTitle: {
    fontWeight: 'bold',
    fontSize: 18,
    marginBottom: 10,
  },
  subSectionTitle: {
    fontStyle: 'italic',
    fontSize: 14,
    color: '#555',
    marginTop: 12,
  },
  secondaryText: {
    color: '#555',
    textAlign: 'center',
    fontSize: 14,
  },
  grid: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-between',
  },
  gridItem: {
    width: '48%',
    marginBottom: 10,
    fontWeight: '500',
  },
  row: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginTop: 8,
  },
  serviceItem: {
    fontSize: 15,
    fontWeight: '500',
  },
  recommendCard: {
    flexDirection: 'row',
    alignItems: 'center',
    marginTop: 10,
  },
  storeLogo: {
    width: 32,
    height: 32,
    marginRight: 10,
    resizeMode: 'contain',
  },
  recommendText: {
    fontSize: 16,
  },
});
