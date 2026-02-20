#include<bits/stdc++.h>
using namespace std;
int main(){
    int n;
    cin>>n;
    int reverse=0;
    while(n!=0){
        int digit=n%10;
n/=10;
if(reverse>INT_MAX/10||reverse<INT_MIN/10){
    cout << 0;
    return 0;
}
if(reverse==INT_MAX/10&&digit>7) {
cout << 0;
 return 0;
}        if(reverse==INT_MIN/10&&digit<-8) {
        return 0;
        }
        reverse=reverse*10+digit;
    }
    cout<<reverse;
    return 0;
}