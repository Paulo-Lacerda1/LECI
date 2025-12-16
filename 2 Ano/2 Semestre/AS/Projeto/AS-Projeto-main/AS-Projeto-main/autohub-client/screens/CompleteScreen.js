import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity, Alert, Image } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';

export default function ReviewConfirmationScreen({ route, navigation }) {
    const { booking, index } = route.params;

    const markCompleted = async () => {
        try {
            const stored = await AsyncStorage.getItem('bookings');
            const all = stored ? JSON.parse(stored) : [];


            const realIndex = all.findIndex(b =>
                b.title === booking.title &&
                b.date === booking.date &&
                b.store === booking.store
            );

            if (realIndex === -1) throw new Error('Booking not found');

            all[realIndex] = { ...all[realIndex], completed: true };
            await AsyncStorage.setItem('bookings', JSON.stringify(all));
            navigation.navigate('Home');
        } catch (e) {
            console.error('Error:', e);
            Alert.alert('Error', 'Could not mark as completed.');
        }
    };

    const proceedToReview = async () => {
        try {
            const stored = await AsyncStorage.getItem('bookings');
            const all = stored ? JSON.parse(stored) : [];

            const realIndex = all.findIndex(b =>
                b.title === booking.title &&
                b.date === booking.date &&
                b.store === booking.store
            );

            if (realIndex === -1) {
                Alert.alert('Erro', 'Agendamento não encontrado.');
                return;
            }

            all[realIndex] = { ...all[realIndex], completed: true };
            await AsyncStorage.setItem('bookings', JSON.stringify(all));

            navigation.navigate('ReviewScreen', { booking: all[realIndex] });
        } catch (e) {
            console.error('Error:', e);
            Alert.alert('Erro', 'Não foi possível completar o agendamento.');
        }
    };


    return (
        <View style={styles.container}>
            {/* Header fixo */}
            <View style={styles.header}>
                <TouchableOpacity onPress={() => navigation.goBack()} style={styles.navIcon}>
                    <Image source={require('../assets/arrowleft.png')} style={styles.backIcon} />
                </TouchableOpacity>
                <Image source={require('../assets/logo_transparente.png')} style={styles.logo} />
            </View>
            <View style={styles.content}>
                <Text style={styles.text}>Do you want to submit a review for this service?</Text>
                <View style={styles.buttonRow}>
                    <TouchableOpacity style={[styles.button, styles.skip]} onPress={markCompleted}>
                        <Text style={styles.buttonText}>Skip Review</Text>
                    </TouchableOpacity>
                    <TouchableOpacity style={[styles.button, styles.proceed]} onPress={proceedToReview}>
                        <Text style={styles.buttonText}>Yes, Review</Text>
                    </TouchableOpacity>
                </View>
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
    },
    header: {
        flexDirection: 'row',
        alignItems: 'center',
        paddingTop: 50,
        paddingHorizontal: 15,
        paddingBottom: 10,
        borderBottomWidth: 1,
        borderColor: '#ddd',
        backgroundColor: '#fff',
    },
    content: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        padding: 20,
    },

    navIcon: {
        paddingRight: 10,
    },
    backIcon: {
        width: 24,
        height: 24,
        resizeMode: 'contain',
    },
    logo: {
        width: 150,
        height: 50,
        resizeMode: 'contain',
        marginLeft: 'auto',
        marginRight: 'auto',
    },
    text: {
        fontSize: 20,
        textAlign: 'center',
        marginBottom: 30
    },
    buttonRow: {
        flexDirection: 'row'
    },
    button: {
        padding: 15,
        borderRadius: 5,
        marginHorizontal: 10,
        minWidth: 120,
        alignItems: 'center',
    },
    skip: {
        backgroundColor: '#999'
    },
    proceed: {
        backgroundColor: '#4CAF50'
    },
    buttonText: {
        color: '#fff',
        fontWeight: '600'
    },
});
