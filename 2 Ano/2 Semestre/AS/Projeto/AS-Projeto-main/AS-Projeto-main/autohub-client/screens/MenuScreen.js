// screens/MenuScreen.js
import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity, Image, ScrollView } from 'react-native';
import { useNavigation } from '@react-navigation/native';

export default function MenuScreen() {
  const navigation = useNavigation();

  const items = [
    { label: 'Profile', icon: require('../assets/menu_icons/profile.png'), screen: 'ClientProfile' },
    { label: 'Settings', icon: require('../assets/menu_icons/settings.png') },
    { label: 'Scheduled services', icon: require('../assets/menu_icons/calendar.png') },
    { label: 'Payments', icon: require('../assets/menu_icons/payments.png') },
    { label: 'Favorites', icon: require('../assets/menu_icons/favorites.png') },
    { label: 'Historic', icon: require('../assets/menu_icons/historic.png'), screen: 'Historic' },
    { label: 'Help', icon: require('../assets/menu_icons/help.png') },
    { label: 'Logout', icon: require('../assets/menu_icons/logout.png'), screen: 'Login' }
  ];

  return (
    <ScrollView style={styles.container} contentContainerStyle={styles.content}>
      <Image source={require('../assets/logo_transparente.png')} style={styles.logo} />

      {items.map((item, index) => (
        <TouchableOpacity
          key={index}
          style={styles.menuItem}
          onPress={() => item.screen && navigation.navigate(item.screen)}
        >
          <Image source={item.icon} style={styles.icon} />
          <Text style={styles.label}>{item.label}</Text>
        </TouchableOpacity>
      ))}
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#F5F5F5',
  },
  content: {
    paddingHorizontal: 20,
    paddingTop: 30,
  },
  logo: {
    width: 180,
    height: 80,
    resizeMode: 'contain',
    alignSelf: 'center',
    marginBottom: 30,
  },
  menuItem: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingVertical: 14,
    borderBottomWidth: 0.5,
    borderBottomColor: '#ccc',
  },
  icon: {
    width: 22,
    height: 22,
    marginRight: 16,
  },
  label: {
    fontSize: 16,
  },
});
