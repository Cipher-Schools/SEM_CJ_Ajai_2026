#include <iostream>
#include<vector>
using namespace std;
class Solution {
public:
    vector<int> shuffle(vector<int>& nums, int n) {
        vector<int>ans;
        for(int i=0;i<n;i++){
            ans.push_back(nums[i]);
            ans.push_back(nums[i+n]);
        }
        return ans;
    }
};
int main(){
    int n;
    cin>>n;
    vector<int>nums(2*n);
    for(int i=0;i<2*n;i++)
        cin>>nums[i];
    Solution s;
    vector<int>ans=s.shuffle(nums,n);
    for(int i=0;i<ans.size();i++)
        cout<<ans[i]<<" ";
}