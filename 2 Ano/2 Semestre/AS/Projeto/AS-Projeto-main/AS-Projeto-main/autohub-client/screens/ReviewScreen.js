import React, { useState } from 'react';
import AsyncStorage from '@react-native-async-storage/async-storage';
import {
    View,
    Text,
    StyleSheet,
    TouchableOpacity,
    Image,
    TextInput,
    Alert,
} from 'react-native';

export default function ReviewScreen({ route, navigation }) {
    const { booking } = route.params;
    const [reviewText, setReviewText] = useState('');
    const [rating, setRating] = useState(0);

    const submitReview = async () => {
        if (!rating || !reviewText.trim()) {
            Alert.alert('Incomplete', 'Please provide a rating and a comment.');
            return;
        }

        try {
            const stored = await AsyncStorage.getItem('bookings');
            const all = stored ? JSON.parse(stored) : [];

            const index = all.findIndex(b =>
                b.title === booking.title &&
                b.date === booking.date &&
                b.store === booking.store
            );

            if (index === -1) throw new Error('Booking not found');

            all[index] = {
                ...all[index],
                completed: true,
                review: {
                    id: Date.now(), // identificador único
                    rating,
                    title: booking.title,
                    text: reviewText.trim(),
                    user: booking.user || 'Anonymous', // ajusta conforme necessário
                    date: new Date().toLocaleDateString(),
                },
            };

            await AsyncStorage.setItem('bookings', JSON.stringify(all));

            Alert.alert('Thank you!', 'Your review has been submitted.');
            navigation.navigate('Home');
        } catch (e) {
            console.error(e);
            Alert.alert('Error', 'Could not save your review.');
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

            {/* Conteúdo */}
            <View style={styles.content}>
                <Text style={styles.title}>Rate your experience with:</Text>
                <Text style={styles.bookingInfo}>{booking.title} @ {booking.store}</Text>

                {/* Estrelas */}
                <View style={styles.starsContainer}>
                    {[1, 2, 3, 4, 5].map((i) => (
                        <TouchableOpacity key={i} onPress={() => setRating(i)}>
                            <Text style={styles.star}>{rating >= i ? '⭐' : '☆'}</Text>
                        </TouchableOpacity>
                    ))}
                </View>

                {/* Input de texto */}
                <TextInput
                    style={styles.textInput}
                    placeholder="Write your feedback..."
                    multiline
                    numberOfLines={4}
                    value={reviewText}
                    onChangeText={setReviewText}
                />

                {/* Botão */}
                <TouchableOpacity style={styles.submitButton} onPress={submitReview}>
                    <Text style={styles.submitButtonText}>Submit Review</Text>
                </TouchableOpacity>
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
    content: {
        flex: 1,
        padding: 20,
        alignItems: 'center',
    },
    title: {
        fontSize: 18,
        fontWeight: '600',
        marginBottom: 10,
    },
    bookingInfo: {
        fontSize: 16,
        color: '#666',
        marginBottom: 20,
    },
    starsContainer: {
        flexDirection: 'row',
        marginBottom: 20,
    },
    star: {
        fontSize: 32,
        marginHorizontal: 5,
    },
    textInput: {
        borderWidth: 1,
        borderColor: '#ccc',
        borderRadius: 10,
        padding: 10,
        width: '100%',
        minHeight: 100,
        textAlignVertical: 'top',
        marginBottom: 20,
    },
    submitButton: {
        backgroundColor: '#4CAF50',
        padding: 15,
        borderRadius: 8,
        alignItems: 'center',
        width: '100%',
    },
    submitButtonText: {
        color: '#fff',
        fontWeight: '600',
        fontSize: 16,
    },
});
