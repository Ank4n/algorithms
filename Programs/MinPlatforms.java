public class MinPlatforms {

    private static class Cab {
      
        private double arrHours;
        private double deptHours;

        private Cab(String arrival, String departure) {

            String[] arr = arrival.split(":");
            String[] dept = departure.split(":");
            this.arrHours = Double.parseDouble(arr[0]) + Double.parseDouble(arr[1]) / 60;
            this.deptHours = Double.parseDouble(dept[0]) + Double.parseDouble(dept[1]) / 60;

        }

        private boolean conflicts(Cab that) {

            if (this.arrHours > that.deptHours)
                return false;
        
            if (this.deptHours < that.arrHours)
                return false;

            return true;
        }
    }

    public static int calculateMinPlatform(String[] arr, String[] dept) {

        assert(arr.length == dept.length);

        if (arr.length == 0)
            return 0;

        int minPlatforms = 1;
        Cab[] cabs = new Cab[arr.length];

        // initialize array of cabs
        for (int i = 0; i < arr.length; i++)
            cabs[i] = new Cab(arr[i], dept[i]);

        
        for (int i = 0; i < cabs.length - 1; i++) {

            int temp = 1;

            for (int j = i + 1; j < cabs.length; j++) {

                if (cabs[i].conflicts(cabs[j])){
                
                    // if i and j are consecutive cabs, just increment the count
                    if (j-i == 1){
                        temp++;
                        continue;
                    }
                    
                    int k = i+1;
                    
                    boolean multipleConflicts = true;
        
                    // check if all of the cabs between i and j conflicts
                    while (k < j){
                        if (!cabs[k++].conflicts(cabs[j])){
                            multipleConflicts = false;
                            break;
                        }
                    }
                    
                    // if all the cabs between i and j conflicts, increment counter
                    if (multipleConflicts)
                    temp++;
                }
                
                else
                    break;

            }
            
            // update minimum platform value  
            if (temp > minPlatforms)
                minPlatforms = temp;
        }

        return minPlatforms;
    }

    public static void main(String[] args) {

        String[] arr = { "9:00", "9:40", "9:50", "11:00", "15:00", "18:00" };
        String[] dep = { "9:10", "12:00", "11:20", "11:30", "19:00", "20:00" };
        System.out.println(calculateMinPlatform(arr, dep));
    }

}
