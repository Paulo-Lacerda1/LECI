// MainTabs.js
import React from 'react';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { Image } from 'react-native';   

import HomeScreen from './HomeScreen';
import SearchScreen from './SearchScreen';
import InboxScreen from './InboxScreen';
import MenuScreen from './MenuScreen';

const Tab = createBottomTabNavigator();

export default function MainTabs() {
  return (
    <Tab.Navigator
      screenOptions={({ route }) => ({
        headerShown: false,
        tabBarIcon: ({ focused }) => {
          let iconSource;
          if (route.name === 'Home') iconSource = focused ? require('../assets/menu_icons/home_active.png') :  require('../assets/menu_icons/home_inactive.png');
          else if (route.name === 'Search') iconSource = focused ? require('../assets/menu_icons/search_active.png') :  require('../assets/menu_icons/search_inactive.png');
          else if (route.name === 'Inbox') iconSource = focused ? require('../assets/menu_icons/inbox_active.png') :  require('../assets/menu_icons/inbox_inactive.png');
          else if (route.name === 'Menu') iconSource = focused ? require('../assets/menu_icons/menu_active.png') :  require('../assets/menu_icons/menu_inactive.png');
          
          return <Image source={iconSource} style={{ width: 24, height: 24 }} />;
        },
        tabBarActiveTintColor: 'black',
        tabBarInactiveTintColor: 'gray',
      })}
    >
      <Tab.Screen name="Home" component={HomeScreen} />
      <Tab.Screen name="Search" component={SearchScreen} />
      <Tab.Screen name="Inbox" component={InboxScreen} />
      <Tab.Screen name="Menu" component={MenuScreen} />
    </Tab.Navigator>
  );
}