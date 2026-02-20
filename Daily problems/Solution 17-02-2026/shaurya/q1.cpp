class Solution {
public:
    bool isHappy(int n) {
        int sum=0;
        while(n>0){
            int r=n%10;
            sum+=r*r;
            n/=10;
        
        }
        if(sum==1){
            return true;
        }
        if(sum==4){
            return false;
        }
        
    
      return  isHappy(sum);
    }
};