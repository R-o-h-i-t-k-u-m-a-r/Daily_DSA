

//Leetcode: 2429. Minimize XOR


class Solution {

    public boolean isSet(int num,int bit){
        return (num & (1<<bit))!=0;
    }

    public int setBit(int num,int bit){
        return (num | (1<<bit));
    }

    public int unsetBit(int num,int bit){
        return num & ~(1<<bit);
    }
    public int minimizeXor(int num1, int num2) {
        //Approach 1: (Starting from num1 and then forming result)
        //T.C : log(n)--> total number of bits
        // int result = num1;
        // int requiredSetBitCount = Integer.bitCount(num2);//total number of set bits in num2
        // int currSetBitCount = Integer.bitCount(num1);//total number of set bits in num1
        // int bit = 0;

        // if(currSetBitCount<requiredSetBitCount){//if current set bit is less then required then
        //     while(currSetBitCount<requiredSetBitCount){
        //         if(!isSet(result,bit)){
        //             result = setBit(result,bit);//set bit from LSB so that we could get minimium value
        //             //while doing xor with num1
        //             currSetBitCount++;
        //         }
        //         bit++;
        //     }
        // }
        // else if(currSetBitCount>requiredSetBitCount){
        //     while(currSetBitCount > requiredSetBitCount){
        //         if(isSet(result,bit)){
        //             result = unsetBit(result,bit);//unset bit from LSB so than we could get minimium
        //             //value while doint xor with num1
        //             currSetBitCount--;
        //         }
        //         bit++;
        //     }
        // }

        // return result;

        //Approach 2:(Directly build result)
        //T.C : log(n)
        int requiredSetBitCount = Integer.bitCount(num2);
        int result = 0;
        //set the bit in result from MSB of num1
        for(int bit = 31; bit>=0 && requiredSetBitCount>0;bit--){
            if(isSet(num1,bit)){
                result = setBit(result,bit);
                requiredSetBitCount--;
            }
        }
        //if any bit remain to set then set bit from LSB 
        for(int bit = 0;bit<32 && requiredSetBitCount>0;bit++){
            if(!isSet(num1,bit)){
                result = setBit(result,bit);
                requiredSetBitCount--;
            }
        }

        return result;
    }

    
}