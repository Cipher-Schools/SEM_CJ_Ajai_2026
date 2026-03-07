#include<iostream>
#include<vector>
using namespace std;
class Solution {
public:
    bool containsNearbyDuplicate(vector<int>& nums, int k) {

        for(int i = 0; i < nums.size(); i++){

            for(int j = i + 1; j-i<=k && j < nums.size(); j++){

                if(nums[i] == nums[j]){
                    return true;
                }

            }

        }

        return false;
    }
};
int main(){
    int n;
    cin>>n;
    int k;
    cin>>k;
    vector<int>nums(n);
    for(int i=0;i<n;i++){
        cin>>nums[i];
    }
    Solution s;
    if(s.containsNearbyDuplicate(nums,k)){
        cout<<"true";
    }
    else{
        cout<<"false";
    }
}