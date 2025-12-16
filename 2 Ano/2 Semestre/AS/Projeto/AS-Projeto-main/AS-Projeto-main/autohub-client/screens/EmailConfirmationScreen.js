import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity, Image } from 'react-native';

export default function EmailConfirmationScreen({ navigation }) {
  return (
    <View style={styles.container}>
      <Image source={require('../assets/logo_transparente.png')} style={styles.logo} />
      <Text style={styles.text}>We've sent a verification link to your email address.</Text>
      <Text style={styles.text}>Click the link to confirm your email and continue using AutoHub.</Text>

      <View style={styles.buttonContainer}>
        <TouchableOpacity style={styles.button} onPress={() => {}}>
                      <Text style={styles.buttonText}>Resend email</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.button} onPress={() => navigation.navigate('Login')}>
                      <Text style={styles.buttonText}>Sign in</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20,
    backgroundColor: '#F5F5F5'
  },
  logo: {
    width: 180,
    height: 100,
    resizeMode: 'contain',
    alignSelf: 'center',
    marginBottom: 30
  },
  text: {
    fontSize: 16,
    textAlign: 'center',
    marginBottom: 10
  },
  buttonContainer: {
    marginTop: 20,
    flexDirection: 'row',
    justifyContent: 'space-around'
  },
  button: {
    backgroundColor: '#222',
    padding: 10,
    borderRadius: 6,
    alignItems: 'center',
    marginTop: 8
  },
  buttonText: {
    color: '#fff',
    fontWeight: 'bold'
  }
});
