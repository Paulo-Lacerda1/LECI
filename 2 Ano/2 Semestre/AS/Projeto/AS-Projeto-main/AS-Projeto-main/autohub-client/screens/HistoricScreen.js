// screens/HistoryScreen.js
import React, { useEffect, useState } from 'react';
import {
    View,
    Text,
    StyleSheet,
    ScrollView,
    TouchableOpacity,
    Image,
    Alert,
} from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';

export default function BookingHistoryScreen({ navigation }) {
    const [bookings, setBookings] = useState([]);

    useEffect(() => {
        const fetchBookings = async () => {
            try {
                const stored = await AsyncStorage.getItem('bookings');
                const all = stored ? JSON.parse(stored) : [];
                const completed = all.filter(b => b.completed);
                setBookings(completed.reverse()); // mostrar mais recentes primeiro
            } catch (error) {
                console.error('Failed to load bookings', error);
                Alert.alert('Error', 'Could not load bookings.');
            }
        };

        const unsubscribe = navigation.addListener('focus', fetchBookings);
        return unsubscribe;
    }, [navigation]);

    return (
        <ScrollView style={styles.container}>
            <Image source={require('../assets/logo_transparente.png')} style={styles.logo} />

            <TouchableOpacity onPress={() => navigation.goBack()} style={styles.navIcon}>
                <Image source={require('../assets/arrowleft.png')} style={styles.backIcon} />
            </TouchableOpacity>

            <Text style={styles.heading}>Booking History</Text>

            {bookings.length === 0 ? (
                <Text style={styles.noData}>No completed bookings found.</Text>
            ) : (
                bookings.map((b, index) => {
                    const dateObj = new Date(b.date);
                    return (
                        <View key={index} style={styles.card}>
                            <Text style={styles.title}>{b.title}</Text>
                            <Text style={styles.label}>Date:</Text>
                            <Text style={styles.value}>
                                {dateObj.toLocaleDateString()} at{' '}
                                {dateObj.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}
                            </Text>
                            <Text style={styles.label}>Vehicle:</Text>
                            <Text style={styles.value}>{b.vehicleType}</Text>
                            <Text style={styles.label}>Extras:</Text>
                            {b.selectedExtras && b.selectedExtras.length > 0 ? (
                                b.selectedExtras.map((ex, i) => (
                                    <Text key={i} style={styles.extra}>• {ex.label} (+{ex.price}€)</Text>
                                ))
                            ) : (
                                <Text style={styles.value}>None</Text>
                            )}
                            <Text style={styles.total}>Total: {b.total.toFixed(2)}€</Text>
                        </View>
                    );
                })
            )}
        </ScrollView>
    );
}

const styles = StyleSheet.create({
    container: {
        backgroundColor: '#F9F9F9',
        flex: 1,
        padding: 20,
    },
    logo: {
        width: 180,
        height: 80,
        resizeMode: 'contain',
        alignSelf: 'center',
        marginBottom: 10,
    },
    navIcon: {
        position: 'absolute',
        top: 30,
        left: 20,
    },
    backIcon: {
        width: 24,
        height: 24,
    },
    heading: {
        fontSize: 20,
        fontWeight: 'bold',
        marginVertical: 20,
        textAlign: 'center',
    },
    noData: {
        fontStyle: 'italic',
        color: '#777',
        textAlign: 'center',
        marginTop: 40,
    },
    card: {
        backgroundColor: '#fff',
        padding: 15,
        borderRadius: 10,
        marginBottom: 20,
        shadowColor: '#000',
        shadowOpacity: 0.1,
        shadowRadius: 5,
        shadowOffset: { width: 0, height: 2 },
        elevation: 3,
    },
    title: {
        fontSize: 16,
        fontWeight: 'bold',
        marginBottom: 6,
    },
    label: {
        fontSize: 13,
        fontWeight: '600',
        marginTop: 8,
    },
    value: {
        fontSize: 13,
        color: '#333',
    },
    extra: {
        fontSize: 13,
        color: '#555',
        marginLeft: 8,
    },
    total: {
        marginTop: 10,
        fontSize: 15,
        fontWeight: 'bold',
        color: '#000',
    },
});
