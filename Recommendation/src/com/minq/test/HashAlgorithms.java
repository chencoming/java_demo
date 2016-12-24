package com.minq.test;

public class HashAlgorithms {

	 /**
     * 加法hash
     * 
     * @param key
     *            字符串
     * @param prime
     *            一个质数
     * @return hash结果
     */
    public static int additiveHash(String key, int prime) {
        int hash, i;
        for (hash = key.length(), i = 0; i < key.length(); i++)
            hash += key.charAt(i);
        return (hash % prime);
    }
    
    
    /**
     * 旋转hash
     * 
     * @param key
     *            输入字符串
     * @param prime
     *            质数
     * @return hash值
     */
    public static int rotatingHash(String key, int prime) {
        int hash, i;
        for (hash = key.length(), i = 0; i < key.length(); ++i)
            hash = (hash << 4) ^ (hash >> 28) ^ key.charAt(i);
        //return (hash % prime);
        return (hash ^ (hash>>10) ^ (hash>>20));
    }
    
    /**
     * 改进的32位FNV算法1
     * 
     * @param data
     *            字符串
     * @return int值
     */
    public static int FNVHash1(String data) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < data.length(); i++)
            hash = (hash ^ data.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return hash;
    }

    /**
     * RS算法hash
     * 
     * @param str
     *            字符串
     */
    public static int RSHash(String str) {
        int b = 378551;
        int a = 63689;
        int hash = 0;

        for (int i = 0; i < str.length(); i++) {
            hash = hash * a + str.charAt(i);
            a = a * b;
        }

        return (hash & 0x7FFFFFFF);
    }



}
