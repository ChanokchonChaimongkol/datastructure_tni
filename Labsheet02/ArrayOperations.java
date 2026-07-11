public class ArrayOperations {
    public static String[] addArraySize(String[] subjects) {
        String[] newSubjects = new String[subjects.length + 1];
        for (int i = 0; i < subjects.length; i++) {
            newSubjects[i] = subjects[i];
        }
        return newSubjects;
    }

    public static String[] deleteElement(int index, String[] subjects) {
        String[] newSubjects = new String[subjects.length - 1];
        int j = 0;
        for (int i = 0; i < subjects.length; i++) {
            if (i != index) {
                newSubjects[j] = subjects[i];
                j++;
            }
        }
        return newSubjects;
    }

    public static String[] updateElement(int index, String value, String[] subjects) {
        subjects[index] = value;
        return subjects;
    }
}