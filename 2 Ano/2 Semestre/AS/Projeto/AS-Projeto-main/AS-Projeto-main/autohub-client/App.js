// App.js
import { StatusBar } from 'expo-status-bar';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

import LoginScreen from './screens/LoginScreen';
import RegisterOptionsScreen from './screens/RegisterOptionsScreen';
import RegisterUserScreen from './screens/RegisterUserScreen';
import RegisterProviderScreen from './screens/RegisterProviderScreen';
import EmailConfirmationScreen from './screens/EmailConfirmationScreen';

import MainTabs from './screens/MainTabs';
import ClientProfileScreen from './screens/ClientProfileScreen';
import ProviderProfileScreen from './screens/ProviderProfileScreen';

import HomeScreen from './screens/HomeScreen';
import BookingScreen from './screens/BookingScreen';
import BookingSummaryScreen from './screens/BookingSummaryScreen';
import BookingDetailsScreen from './screens/BookingDetailsScreen';
import CancelConfirmationScreen from './screens/CancelConfirmationScreen';
import CompleteScreen from './screens/CompleteScreen';
import ReviewScreen from './screens/ReviewScreen';
import HistoricScreen from './screens/HistoricScreen';

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Login">
        <Stack.Screen name="Login" component={LoginScreen} options={{ headerShown: false }} />
        <Stack.Screen name="RegisterOptions" component={RegisterOptionsScreen} options={{ headerShown: false }} />
        <Stack.Screen name="RegisterUser" component={RegisterUserScreen} options={{ headerShown: false }} />
        <Stack.Screen name="RegisterProvider" component={RegisterProviderScreen} options={{ headerShown: false }} />
        <Stack.Screen name="EmailConfirmation" component={EmailConfirmationScreen} options={{ headerShown: false }} />

        <Stack.Screen name="ClientProfile" component={ClientProfileScreen} options={{ headerShown: false }} />
        <Stack.Screen name="ProviderProfile" component={ProviderProfileScreen} options={{ headerShown: false }} />
        <Stack.Screen name="Main" component={MainTabs} options={{ headerShown: false }} />

        <Stack.Screen name="Home" component={HomeScreen} options={{ headerShown: false }} />
        <Stack.Screen name="BookingScreen" component={BookingScreen} options={{ headerShown: false }} />
        <Stack.Screen name="BookingSummaryScreen" component={BookingSummaryScreen} options={{ headerShown: false }} />
        <Stack.Screen name="BookingDetailsScreen" component={BookingDetailsScreen} options={{ headerShown: false }} />
        <Stack.Screen name="CancelConfirmationScreen" component={CancelConfirmationScreen} options={{ headerShown: false }} />
        <Stack.Screen name="CompleteScreen" component={CompleteScreen} options={{ headerShown: false }} />
        <Stack.Screen name="ReviewScreen" component={ReviewScreen} options={{ headerShown: false }} />
        <Stack.Screen name="Historic" component={HistoricScreen} options={{ headerShown: false }} />

      </Stack.Navigator>
      <StatusBar style="auto" />
    </NavigationContainer>
  );
}
