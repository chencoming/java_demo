package com.minq.test;

public class HashAlgorithms {

	 /**
     * �ӷ�hash
     * 
     * @param key
     *            �ַ���
     * @param prime
     *            һ������
     * @return hash���
     */
    public static int additiveHash(String key, int prime) {
        int hash, i;
        for (hash = key.length(), i = 0; i < key.length(); i++)
            hash += key.charAt(i);
        return (hash % prime);
    }
    
    
    /**
     * ��תhash
     * 
     * @param key
     *            �����ַ���
     * @param prime
     *            ����
     * @return hashֵ
     */
    public static int rotatingHash(String key, int prime) {
        int hash, i;
        for (hash = key.length(), i = 0; i < key.length(); ++i)
            hash = (hash << 4) ^ (hash >> 28) ^ key.charAt(i);
        //return (hash % prime);
        return (hash ^ (hash>>10) ^ (hash>>20));
    }
    
    /**
     * �Ľ���32λFNV�㷨1
     * 
     * @param data
     *            �ַ���
     * @return intֵ
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
     * RS�㷨hash
     * 
     * @param str
     *            �ַ���
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
