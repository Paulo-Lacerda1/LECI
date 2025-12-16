import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet, Image, TouchableOpacity } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';

export default function ClientProfileScreen({ navigation }) {
  const [profile, setProfile] = useState(null);

  useEffect(() => {
    const loadUserData = async () => {
      try {
        const storedData = await AsyncStorage.getItem('session'); // <- aqui
        if (storedData) {
          const parsedData = JSON.parse(storedData);
          setProfile(parsedData);
        }
      } catch (error) {
        console.error('Erro ao carregar os dados do utilizador', error);
      }
    };

    loadUserData();
  }, []);


  if (!profile) {
    return (
      <View style={styles.container}>
        <Text style={{ padding: 20 }}>Loading profile...</Text>
      </View>
    );
  }

  // Geração de iniciais para o círculo
  const initials = (profile.firstName[0] + (profile.lastName?.[0] || '')).toUpperCase();

  return (
    <View style={styles.container}>
      <Image source={require('../assets/logo_transparente.png')} style={styles.logo} />

      <View style={styles.header}>
        <TouchableOpacity onPress={() => navigation.goBack()} style={styles.navIcon}>
          <Image source={require('../assets/arrowleft.png')} style={styles.backIcon} />
        </TouchableOpacity>
        <TouchableOpacity>
          <Image source={require('../assets/menu_icons/edit.png')} style={styles.navIcon} />
        </TouchableOpacity>
      </View>

      <View style={styles.profile}>
        <View style={styles.initialsCircle}>
          <Text style={styles.initials}>{initials}</Text>
        </View>

        <Text style={styles.label}>First Name</Text>
        <Text style={styles.value}>{profile.firstName}</Text>

        <Text style={styles.label}>Last Name</Text>
        <Text style={styles.value}>{profile.lastName}</Text>

        <Text style={styles.label}>NIF</Text>
        <Text style={styles.value}>{profile.nif}</Text>

        <Text style={styles.label}>Phone</Text>
        <Text style={styles.value}>{profile.phone}</Text>

        <Text style={styles.label}>Email</Text>
        <Text style={styles.value}>{profile.email}</Text>

        <Text style={styles.label}>Address</Text>
        <Text style={styles.value}>{profile.address}</Text>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#F5F5F5',
    flex: 1,
    paddingTop: 30,
  },
  logo: {
    width: 180,
    height: 80,
    resizeMode: 'contain',
    alignSelf: 'center',
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    paddingHorizontal: 20,
    marginVertical: 10,
  },
  navIcon: {
    width: 20,
    height: 20,
  },
  backIcon: {
    width: 20,
    height: 20,
  },
  profile: {
    paddingHorizontal: 30,
  },
  initialsCircle: {
    width: 60,
    height: 60,
    borderRadius: 30,
    backgroundColor: '#ccc',
    justifyContent: 'center',
    alignItems: 'center',
    marginVertical: 20,
  },
  initials: {
    fontSize: 20,
    color: '#fff',
    fontWeight: 'bold',
  },
  label: {
    fontSize: 13,
    color: '#666',
    marginTop: 10,
  },
  value: {
    fontSize: 16,
    fontWeight: 'bold',
  },
});
