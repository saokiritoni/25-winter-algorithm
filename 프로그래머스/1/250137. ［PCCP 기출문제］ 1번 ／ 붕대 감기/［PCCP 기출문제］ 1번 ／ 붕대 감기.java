class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
     
        int attackIdx = 0;
        int success = 0;
        int lastAttack = attacks[attacks.length - 1][0];
        int nowHealth = health;
        
        for (int i = 1; i <= lastAttack; i++) {
                if (i != attacks[attackIdx][0]) {
                    nowHealth += bandage[1];
                    success++;
                    if (success == bandage[0]) {
                        nowHealth += bandage[2];
                        success = 0;
                    }
                    if (nowHealth > health) {nowHealth = health;}
                } else {
                    success = 0;
                    nowHealth -= attacks[attackIdx][1];
                    if (nowHealth <= 0) {return -1;}
                    attackIdx++;
                }
            }
        return nowHealth;
        }
    }
