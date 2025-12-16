import { StatusBar } from 'expo-status-bar';
import {  View, Text, StyleSheet, TextInput, TouchableOpacity, Image, Alert } from 'react-native';
import React, { useState } from 'react';

import AsyncStorage from '@react-native-async-storage/async-storage';

const MOCK_USER = {
  firstName: 'Maria',
  lastName: 'Santos',
  email: '',
  password: '',
  confirm: '123456',
  nif: '987654321',
  phone: '912345678',
  address: 'Rua das Flores, 45'
};

export default function LoginScreen({ navigation }) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = async () => {
    try {
      const storedUser = await AsyncStorage.getItem('user');
      const user = storedUser ? JSON.parse(storedUser) : null;

      const isMockLogin = email === MOCK_USER.email && password === MOCK_USER.password;
      const isStoredLogin = user && email === user.email && password === user.password;

      if (isMockLogin || isStoredLogin) {
        const loggedUser = isMockLogin ? MOCK_USER : user;
        await AsyncStorage.setItem('session', JSON.stringify(loggedUser));
        navigation.navigate('Main', { screen: 'Home' });
      } else {
        Alert.alert('Erro', 'Email ou senha inválidos');
      }
    } catch (error) {
      console.error('Erro ao recuperar dados do utilizador.', error);
      Alert.alert('Erro', 'Falha ao autenticar utilizador');
    }
  };


  return (
    <View style={styles.container}>
      <Image source={require('../assets/logo_transparente.png')} style={styles.logo} />
      
      <View style = {styles.form}>
        <TextInput
          style = {styles.input}
          placeholder = "Email"
          value = {email}
          onChangeText = {setEmail}
          keyboardType='email-address'
          autoCapitalize='none'
        />

        <TextInput
          style = {styles.input}
          placeholder = "Senha"
          value = {password}
          onChangeText = {setPassword}
          secureTextEntry
        />

        <TouchableOpacity style={styles.button} onPress={handleLogin}>
          <Text style={styles.buttonText}>Sign In</Text>
        </TouchableOpacity>

        <TouchableOpacity>
          <Text style={styles.link}>Forgot password?</Text>
        </TouchableOpacity>

        <TouchableOpacity onPress={() => navigation.navigate('RegisterOptions')}>
          <Text style={styles.link}>Sign up</Text>
        </TouchableOpacity>

      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#F5F5F5',
    alignItems: 'center',
    justifyContent: 'center',
    paddingHorizontal: 20
  },
  logo: {
    width: 180,
    height: 100,
    resizeMode: 'contain',
    marginBottom: 20
  },
  form: {
    width: '100%',
    backgroundColor: '#fff',
    padding: 20,
    borderRadius: 8,
    elevation: 2
  },
  input: {
    height: 40,
    borderColor: '#ccc',
    borderWidth: 1,
    borderRadius: 6,
    marginBottom: 12,
    paddingHorizontal: 10
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
  },
  link: {
    color: '#000',
    textDecorationLine: 'underline',
    textAlign: 'center',
    marginTop: 10
  }
});