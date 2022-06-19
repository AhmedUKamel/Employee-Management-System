package com.example.employeemanagementsystem;
public class EmployeeArrayList {
    // ATTRIBUTES
    public static final int MAX_SIZE = 1000;
    private Employee[] array = new Employee[MAX_SIZE];
    private int filled;
    // METHODS
    public boolean addEmployee(Employee employee) {
        if(filled != MAX_SIZE) {
            array[filled++] = employee;
            return true;
        } else {
            System.out.println("UNABLE TO ADD EMPLOYEE DUO TO ARRAY LENGTH");
            return false;
        }
    }
    public boolean delEmployee(Employee employee) {
        for(int index = 0; index < filled; index++)
            if(array[index].equals(employee)) {
                while(index != filled)
                    array[index++] = array[index];
                filled--;
                return true;
            }
        return false;
    }
    public Employee get(int index) {
        try {
            return array[index];
        } catch (Exception e) {
            System.out.println("INVALID INDEX");
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Employee linearSearch(String target) {
        try {
            Long id = Long.parseLong(target);
            for(int index = 0; index < filled; index++)
                if(array[index].getId().equals(id))
                    return array[index];
        } catch (Exception e) {
            for(int index = 0; index < filled; index++)
                if(array[index].getName().equals(target))
                    return array[index];
        } return null;
    }
    public Employee binarySearch(String target) {
        int left = 0, right = filled - 1, middle;
        try {
            Long id = Long.parseLong(target);
            while(left <= right) {
                middle = (left + right) / 2;
                if(array[middle].getId().equals(id))
                    return array[middle];
                else if(array[middle].getId() > id)
                    right = middle - 1;
                else
                    left = middle + 1;
            }
        } catch (Exception e) {
            while(left <= right) {
                middle = (left + right) / 2;
                if (array[middle].getName().equals(target))
                    return array[middle];
                else if (array[middle].getName().compareTo(target) > 0)
                    right = middle - 1;
                else
                    left = middle + 1;
            }
        } return null;
    }
    public void bubbleSortByName() {
        for(int pass = 0; pass < filled; pass++)
            for(int step = 0; step < pass; step++)
                if(array[step].compareTo(array[step+1]) > 0) {
                    Employee temp = array[step];
                    array[step] = array[step+1];
                    array[step+1] = temp;
                }
    }
    public void bubbleSortById() {
        for (int pass = 0; pass < filled; pass++)
            for (int step = 0; step < filled - 1; step++)
                if (array[step].getId() > array[step + 1].getId()) {
                    Employee temp = array[step];
                    array[step] = array[step + 1];
                    array[step + 1] = temp;
                }
    }
    public void insertionSortByName() {
        for(int pass = 1; pass < filled; pass++) {
            int index = pass - 1;
            Employee key = array[pass];
            while(index >= 0 && array[index].compareTo(array[pass]) > 0)
                array[index+1] = array[index--];
            array[index+1] = key;
        }
    }
    public void insertionSortById() {
        for(int pass = 1; pass < filled; pass++) {
            int index = pass - 1;
            Employee key = array[pass];
            while(index >= 0 && array[index].getId() > array[pass].getId())
                array[index+1] = array[index--];
            array[index+1] = key;
        }
    }
    public void selectionSortByName() {
        for(int pass = 0; pass < filled; pass++) {
            int smallest_index = pass;
            for(int index = pass; index < filled; index++)
                if(array[smallest_index].compareTo(array[index]) > 0)
                    smallest_index = index;
            if(smallest_index != pass) {
                Employee temp = array[smallest_index];
                array[smallest_index] = array[pass];
                array[pass] = temp;
            }
        }
    }
    public void selectionSortById() {
        for(int pass = 0; pass < filled; pass++) {
            int smallest_index = pass;
            for(int index = pass; index < filled; index++)
                if(array[smallest_index].getId() > array[index].getId())
                    smallest_index = index;
            if(smallest_index != pass) {
                Employee temp = array[smallest_index];
                array[smallest_index] = array[pass];
                array[pass] = temp;
            }
        }
    }
    public void mergeSortByName(int left, int right) {
        if (left < right) {
            int middle = (right + left) / 2;
            mergeSortByName(left, middle);
            mergeSortByName(middle + 1, right);
            mergeByName(left, middle, right);
        }
    }
    public void mergeByName(int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        Employee L[] = new Employee[n1];
        Employee R[] = new Employee[n2];
        for (int i = 0; i < n1; ++i)
            L[i] = array[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[m + 1 + j];
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (R[j].compareTo(L[i]) > 0) array[k] = L[i++];
            else array[k] = R[j++];
            k++;
        }
        while (i < n1) array[k++] = L[i++];
        while (j < n2) array[k++] = R[j++];
    }
    public void mergeSortById(int left, int right) {
        if (left < right) {
            int middle = (right + left) / 2;
            mergeSortById(left, middle);
            mergeSortById(middle + 1, right);
            mergeById(left, middle, right);
        }
    }
    public void mergeById(int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        Employee L[] = new Employee[n1];
        Employee R[] = new Employee[n2];
        for (int i = 0; i < n1; ++i)
            L[i] = array[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[m + 1 + j];
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (R[j].getId() > (L[i].getId())) array[k] = L[i++];
            else array[k] = R[j++];
            k++;
        }
        while (i < n1) array[k++] = L[i++];
        while (j < n2) array[k++] = R[j++];
    }
    public void heapSortByName() {
        int n = filled;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapifyByName(n, i);
        for (int i = n - 1; i > 0; i--) {
            Employee temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapifyByName(i, 0);
        }
    }
    public void heapifyByName(int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && array[l].compareTo(array[largest]) > 0)
            largest = l;
        if (r < n && array[r].compareTo(array[largest]) > 0)
            largest = r;
        if (largest != i) {
            Employee swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
            heapifyByName(n, largest);
        }
    }
    public void heapSortById() {
        int n = filled;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapifyById(n, i);
        for (int i = n - 1; i > 0; i--) {
            Employee temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapifyById(i, 0);
        }
    }
    public void heapifyById(int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && array[l].getId() > array[largest].getId())
            largest = l;
        if (r < n && array[r].getId() > array[largest].getId())
            largest = r;
        if (largest != i) {
            Employee swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
            heapifyById(n, largest);
        }
    }
    public void reverse() {
        Employee[] reversed = new Employee[MAX_SIZE];
        for(int i = filled - 1, j = 0; i >= 0; i--, j++)
            reversed[j] = array[i];
        array = reversed;
    }
    public void clear() {
        array = new Employee[MAX_SIZE];
        filled = 0;
    }
    public int length() {
        return filled;
    }
}