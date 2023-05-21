package integrationend.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;


public class MathAlgorithm {
    /**
     * 求平均值
     *
     * @param x
     * @return
     */
    public double getAverage(double[] x) {
        int len = x.length;
        double res;
        double sum = 0;
        for (int i = 0; i < len; i++) {
            sum += x[i];
        }
        res = sum / len;
        return res;
    }

    /**
     * 方差s^2=[(x1-x)^2 +...(xn-x)^2]/n
     *
     * @param x
     * @return
     */
    public double getVariance(double[] x) {
        int len = x.length;
        double dAve = getAverage(x);
        double dVar = 0;
        for (int i = 0; i < len; i++) {// 求方差
            dVar += (x[i] - dAve) * (x[i] - dAve);
        }
        return dVar / len;
    }

    /**
     * 标准差σ=sqrt(s^2)
     *
     * @param x
     * @return
     */
    public double getStandardDiviation(double[] x) {
        int len = x.length;
        double dVar = getVariance(x);
        return Math.sqrt(dVar / len);
    }

    /**
     * 中位数(int)
     *
     * @param nums: A list of integers.
     * @return: An integer denotes the middle number of the array.
     */
    public int median(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        int index = partition(nums, start, end);
        if (nums.length % 2 == 0) {
            while (index != nums.length / 2 - 1) {
                if (index > nums.length / 2 - 1) {
                    index = partition(nums, start, index - 1);
                } else {
                    index = partition(nums, index + 1, end);
                }
            }
        } else {
            while (index != nums.length / 2) {
                if (index > nums.length / 2) {
                    index = partition(nums, start, index - 1);
                } else {
                    index = partition(nums, index + 1, end);
                }
            }
        }
        return nums[index];
    }

    private int partition(int[] nums, int start, int end) {
        int left = start;
        int right = end;
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            if (left < right) {
                nums[left] = nums[right];
                left++;
            }
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            if (left < right) {
                nums[right] = nums[left];
                right--;
            }
        }
        nums[left] = pivot;
        return left;
    }

    /**
     * 中位数(double)
     *
     * @param nums: A list of double.
     * @return: An double denotes the middle number of the array.
     */
    public double getMedium(double[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        int index = partition(nums, start, end);
        if (nums.length % 2 == 0) {
            while (index != nums.length / 2 - 1) {
                if (index > nums.length / 2 - 1) {
                    index = partition(nums, start, index - 1);
                } else {
                    index = partition(nums, index + 1, end);
                }
            }
        } else {
            while (index != nums.length / 2) {
                if (index > nums.length / 2) {
                    index = partition(nums, start, index - 1);
                } else {
                    index = partition(nums, index + 1, end);
                }
            }
        }
        return nums[index];
    }

    private int partition(double[] nums, int start, int end) {
        int left = start;
        int right = end;
        double pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            if (left < right) {
                nums[left] = nums[right];
                left++;
            }
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            if (left < right) {
                nums[right] = nums[left];
                right--;
            }
        }
        nums[left] = pivot;
        return left;
    }

    /**
     * 众数(int)
     * 众数:在一个数组中出现次数最多的数
     * 如果存在多个众数，则一起返回
     *
     * @param arr
     * @return
     */
    public List<Integer> getMode(int[] arr) {
        int n = arr.length;

        if (n == 0) {
            return new ArrayList<Integer>();
        }

        if (n == 1) {
            return Arrays.asList(arr[0]);
        }

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < n; i++) { // 统计数组中每个数出现的频率
            Integer v = freqMap.get(arr[i]);
            // v == null 说明 freqMap 中还没有这个 arr[i] 这个键
            freqMap.put(arr[i], v == null ? 1 : v + 1);
        }

        // 将 freqMap 中所有的键值对（键为数，值为数出现的频率）放入一个 ArrayList
        List<Entry<Integer, Integer>> entries = new ArrayList<>(freqMap.entrySet());
        // 对 entries 按出现频率从大到小排序
        Collections.sort(entries, new Comparator<Entry<Integer, Integer>>() {
            @Override
            public int compare(Entry<Integer, Integer> e1, Entry<Integer, Integer> e2) {
                return e2.getValue() - e1.getValue();
            }
        });

        List<Integer> modalNums = new ArrayList<>();
        modalNums.add(entries.get(0).getKey()); // 排序后第一个 entry 的键肯定是一个众数

        int size = entries.size();
        for (int i = 1; i < size; i++) {
            // 如果之后的 entry 与第一个 entry 的 value 相等，那么这个 entry 的键也是众数
            if (entries.get(i).getValue().equals(entries.get(0).getValue())) {
                modalNums.add(entries.get(i).getKey());
            } else {
                break;
            }
        }

        return modalNums;
    }

    /**
     * 众数(double)
     * 众数:在一个数组中出现次数最多的数
     * 如果存在多个众数，则一起返回
     *
     * @param arr
     * @return
     */
    public List<Double> getMode(double[] arr) {
        int n = arr.length;

        if (n == 0) {
            return new ArrayList<Double>();
        }

        if (n == 1) {
            return Arrays.asList(arr[0]);
        }

        Map<Double, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < n; i++) { // 统计数组中每个数出现的频率
            Integer v = freqMap.get(arr[i]);
            // v == null 说明 freqMap 中还没有这个 arr[i] 这个键
            freqMap.put(arr[i], v == null ? 1 : v + 1);
        }

        // 将 freqMap 中所有的键值对（键为数，值为数出现的频率）放入一个 ArrayList
        List<Entry<Double, Integer>> entries = new ArrayList<>(freqMap.entrySet());
        // 对 entries 按出现频率从大到小排序
        Collections.sort(entries, new Comparator<Entry<Double, Integer>>() {
            @Override
            public int compare(Entry<Double, Integer> e1, Entry<Double, Integer> e2) {
                return e2.getValue() - e1.getValue();
            }
        });

        List<Double> modalNums = new ArrayList<>();
        modalNums.add(entries.get(0).getKey()); // 排序后第一个 entry 的键肯定是一个众数

        int size = entries.size();
        for (int i = 1; i < size; i++) {
            // 如果之后的 entry 与第一个 entry 的 value 相等，那么这个 entry 的键也是众数
            if (entries.get(i).getValue().equals(entries.get(0).getValue())) {
                modalNums.add(entries.get(i).getKey());
            } else {
                break;
            }
        }

        return modalNums;
    }

    /**
     * double 和 int 数字排序
     *
     * @param n
     */
    public void orderNum(double[] n) {

        for (int i = 0; i < n.length - 1; i++) {
            for (int j = 0; j < n.length - 1 - i; j++) {
                double temp = 0;
                if (n[j] > n[j + 1]) {
                    temp = n[j + 1];
                    n[j + 1] = n[j];
                    n[j] = temp;
                }
            }
        }
    }

}
