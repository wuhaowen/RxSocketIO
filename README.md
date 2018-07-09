# RxSocketIO

This library allows the usage of RxJava with the Socket.IO client.

## Setup
```
##project build.gradle
allprojects {
    repositories {
        .....
        maven{ url 'https://dl.bintray.com/smilekiller/RxSocketIO/'}
    }
}

##app build.gradle
dependencies {
    implementation 'com.wuhaowen:rxsocket.io:1.0.0'
    
    implementation "io.reactivex.rxjava2:rxjava:2.1.16"
    implementation ('io.socket:socket.io-client:1.0.0') {
        exclude group: 'org.json', module: 'json'
    }
    
    implementation 'com.android.support:appcompat-v7:27.1.1'
    implementation 'com.android.support:support-annotations:27.1.1'

}
```

## Usage
Create a Socket.IO client instance:
```
mSocket = IO.socket(path, opts);

```
Example1:
```
RxSocketIO.on(mSocket, "foo").subscribe(msg -> {
  Log.d("rx", msg.getEventName())// event name
  Log.d("rx", msg.getArgs())// data 
})
```

Example2:
```
RxSocketIO.once(mSocket, "foo").subscribe(msg -> {
  Log.d("rx", msg.getEventName())// event name
  Log.d("rx", msg.getArgs())// data 
})
```

Example3: // listen many events
```
RxSocketIO.on(mSocket, "foo", Socket.EVENT_RECONNECT,
                Socket.EVENT_RECONNECT_ATTEMPT,
                Socket.EVENT_RECONNECT_ERROR).subscribe(msg -> {
  Log.d("rx", msg.getEventName())// event name
  Log.d("rx", msg.getArgs())// data 
})
```
