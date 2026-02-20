class Solution {
public:
    int divide(int dividend, int divisor) {
       if(dividend==INT_MIN&&divisor==-1){
        return INT_MAX;
       }
       if(divisor==0){
        return INT_MAX;
       }
        long long a= llabs((long long)dividend);
        long long b= llabs((long long)divisor);
long long l =0;
long long r=a;
long long ans=0;
while(l<=r){
    long long mid=l+(r-l)/2;
    if(mid<=a/b){
        ans=mid;
        l=mid+1;
    }
    else{
        r=mid-1;
    }
}
        
        if ((dividend < 0)^(divisor < 0)){
            ans = -ans;
        }
        
        return (int)ans;

    }
};