import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Box{
    int[]cords;
    boolean filled;
    boolean captured;
    String varCombo;

    public Box(int[] cords, boolean captured,  String varCombo) {
        this.cords = cords;
        this.captured = captured;
        this.varCombo = varCombo;
    }
    public String getCords(){
        return STR."(\{cords[1]}, \{cords[0]})";
    }

    @Override
    public String toString() {
        return STR."Box{cords=\{Arrays.toString(cords)}, filled=\{filled}, captured=\{captured}, varCombo='\{varCombo}'}";
    }
}
class CaptureSquare{
    int[] start;
    int[] shape;
    int[] indexesHash;
    public CaptureSquare(int[]start, int[] shape){
        this.start = start;
        this.shape = shape;
        this.indexesHash = new int[shape[0] * shape[1]];
    }



    public Box[][] getBoxes(Box[][]matrix){
        Box[][]boxes = new Box[shape[0]][shape[1]];
        for(int i = 0; i <shape[0]; i++){
            for(int j = 0; j<shape[1]; j++){
                boxes[i][j] = matrix[(start[0]+i )% matrix.length][(start[1]+j) % matrix[i].length];
            }
        }
        return boxes;
    }

    @Override
    public String toString() {
        return STR."CaptureSquare{start=\{Arrays.toString(start)}, shape=\{Arrays.toString(shape)}}";
    }
}
//box complete return
//class BCReturn{
//    boolean complete;
//    int[]cord;
//
//    public BCReturn(boolean complete, int[] cord) {
//        this.complete = complete;
//        this.cord = cord;
//    }
//}
public class KMapMain {
    static final int ROW = 0;
    static final int COL = 1;
    static final int TOP = 0;
    static final int BOTTOM = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;
    static final int CAP_TO_SMALL_OFFSET = 32;
    static void main() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Number of Variable: ");
        int varAmount = sc.nextInt();
        System.out.print("Enter the Start Variable (letter): ");
        String startLetterInput = sc.next();
        startLetterInput = startLetterInput.toUpperCase();
        sc.nextLine();
        if(startLetterInput.length() != 1){
            System.out.println("must input a char (1 letter ONLY)!");
            return;
        }
        if((int)startLetterInput.toUpperCase().charAt(0) > 26 - varAmount + 65){
            System.out.println("not enough letters in the alphabet!");
            return;
        }
        char startChar = startLetterInput.charAt(0);
        System.out.println("Format to use: \nUPPERCASE -> true(1)\nLOWERCASE -> false(0)");

        System.out.println("VARS TO USE: ");
        char [] vars = new char[varAmount];
        for(int i = 0; i<varAmount; i++){
            vars[i] = (char) (startChar + i);
            System.out.println(vars[i]);
        }

        System.out.println("Enter Equation: ");
        String F = sc.nextLine();

        String regex = "([a-zA-Z]+)\\s*(?:\\+|$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(F);

        List<String> elements = new ArrayList<>();

        // The while loop acts dynamically, running as many times as there are matches
        int count = 0;
        System.out.println("Parsed Elements: ");
        while (matcher.find()) {
            // matcher.group(1) extracts just the letters inside the parentheses ()
            System.out.println(STR."\{count + 1}. \{matcher.group(1)}");
            elements.add(matcher.group(1));
            if(matcher.group(1).length() != varAmount){
                System.out.println("Element " + count + " of the Sum Equation is not Complete! (" + matcher.group(1).length() + " out of " + varAmount + ")");
            }
            count++;
        }

        if(elements.isEmpty()){
            System.out.println("No Elements Parsed!");
            return;
        }


        int[]matrixSize = getMatrixSize(varAmount);



        String [][] cellIndex = new String[2][matrixSize[COL]];

        String[] strCel = new String[2];

        int countIndex = 0;
        for(int i = 0; i<2; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < matrixSize[i] / 2; j++){
                sb.append(vars[countIndex++]);
            }
            strCel[i] = sb.toString();
            System.out.println(strCel[i]);
        }
        HashMap<String, Box> boxes = new HashMap<>();
        for(int i = 0; i < 2; i++){
            /*switch(i){
                case ROW:
                    System.out.println("ROW:");
                    break;
                case COL:
                    System.out.println("COL:");
                    break;
            }*/
            int toToggleCharIndex = 0;
            String prevVarCombo = strCel[i];
            cellIndex[i][0] = strCel[i];
            System.out.println(strCel[i]);
            for(int j = 1 ; j < matrixSize[i]; j++){
                StringBuilder sb = new StringBuilder();
                toToggleCharIndex = toToggleCharIndex + 1 <= strCel[i].length() - 1 ? toToggleCharIndex+1 : 0;
                for(int k = 0; k <strCel[i].length(); k++){
                    char var = prevVarCombo.charAt(k);
                    if(k == toToggleCharIndex){
                        sb.append(isPrime(var) ? (char) (var - CAP_TO_SMALL_OFFSET) : (char)(var + CAP_TO_SMALL_OFFSET));
                        continue;
                    }
                    sb.append(prevVarCombo.charAt(k));
                }
                String varCombo = sb.toString();
                cellIndex[i][j] = varCombo;
                prevVarCombo = varCombo;
//                System.out.println(varCombo);

            }
        }

        Box [][] k_matrix = new Box[matrixSize[ROW]][matrixSize[COL]];
        for(int i = 0; i<matrixSize[ROW]; i++){
            for(int j = 0; j<matrixSize[COL]; j++){
                Box newBox = new Box(new int[]{i, j}, false, cellIndex[ROW][i] + cellIndex[COL][j]);
                boxes.put(cellIndex[ROW][i] + cellIndex[COL][j], newBox);
                k_matrix[i][j] = newBox;
            }
        }



        ArrayList<Box>filledBoxes = new ArrayList<>();

        for(String element : elements){
            k_matrix[boxes.get(element).cords[ROW]][boxes.get(element).cords[COL]].filled = true;
            filledBoxes.add(boxes.get(element));
        }

        printMatrix(k_matrix, cellIndex);

        ArrayList<Box>nonCapturedBoxes = new ArrayList<>(filledBoxes);

        HashMap<Integer, Integer> arrIndexOfMatrixCords = new HashMap<>();
        for(int i = 0; i<nonCapturedBoxes.size(); i++){
            System.out.println("Putting cords" + nonCapturedBoxes.get(i).getCords() + "into arr");
            arrIndexOfMatrixCords.put(Arrays.hashCode(nonCapturedBoxes.get(i).cords), i);
        }

        //loops starts here.

        ArrayList<Box>tlCornerBoxes;

        ArrayList<CaptureSquare>capturedSquares = new ArrayList<>();
        int c= 0;
        while(!nonCapturedBoxes.isEmpty()){
            c++;
//            tlCornerBoxes = nonCapturedBoxes.stream().filter(box -> /*isTLCornerBox(box, k_matrix) &&*/ isNotIsolated(box, k_matrix)).collect(Collectors.toCollection(ArrayList::new));
            System.out.println(STR."TL Corner Boxes Amount: \{nonCapturedBoxes.size()}");
            int [] biggestCordMetrics = new int[2]; //height width
            Box biggestCordStartBox = null; // x y cordinate of the START

            //find biggest box for each start point
            for(Box startBox : filledBoxes) {
                System.out.println("Current SB: " + startBox.getCords());
                int[] metrics = findBiggestBox(startBox.cords, k_matrix, capturedSquares);
                if (sizeBox(metrics) >= sizeBox(biggestCordMetrics)) {
                    biggestCordMetrics = metrics;
                    biggestCordStartBox = startBox;
                }
            }

            if(biggestCordStartBox == null){
                System.out.println("Biggest Capture not Found!");
                break;
            }
            CaptureSquare cs = new CaptureSquare(biggestCordStartBox.cords, biggestCordMetrics);
            capturedSquares.add(cs);
            System.out.println("Working with Capture square" + cs);

            //capture and remove the box from the nonCapturedBoxes
            int[]currentIndex = new int[]{biggestCordStartBox.cords[ROW], biggestCordStartBox.cords[COL]};
            int indexesCount = 0;
            int[]indexesHash = new int[biggestCordMetrics[ROW] * biggestCordMetrics[COL]];
            for(int i = 0; i<biggestCordMetrics[ROW]; i++){
                currentIndex[COL] = biggestCordStartBox.cords[COL];
                for(int j = 0; j<biggestCordMetrics[COL]; j++){
                    k_matrix[currentIndex[ROW]][currentIndex[COL]].captured = true;

                    System.out.println("Removing at cords: " + k_matrix[currentIndex[ROW]][currentIndex[COL]].getCords());
                    int squareHash =Arrays.hashCode(new int[]{currentIndex[ROW], currentIndex[COL]});
                    indexesHash[indexesCount++] = squareHash;
                    int indexToDelete = -1;

                    for(int k = 0; k<nonCapturedBoxes.size(); k++){
                        if(Arrays.hashCode(nonCapturedBoxes.get(k).cords) == squareHash){
                            indexToDelete = k;
                            break;
                        }
                    }
                    if(indexToDelete == -1){
                        System.out.println("Already Previously Deleted");
                        continue;
                    }
                    nonCapturedBoxes.remove(indexToDelete);
                    arrIndexOfMatrixCords.remove(squareHash);

                    if(j != biggestCordMetrics[COL]-1){
                        currentIndex = next(currentIndex, k_matrix, RIGHT);
                    }
                }
                if(i != biggestCordMetrics[ROW]-1){
                    currentIndex = next(currentIndex, k_matrix, BOTTOM);
                }
            }

            cs.indexesHash = indexesHash;
        }

        //handle isolated
        for(Box box : nonCapturedBoxes){
            capturedSquares.add(new CaptureSquare(box.cords, new int[]{1,1}));
            k_matrix[box.cords[ROW]][box.cords[COL]].captured = true;
        }

        System.out.println("Capture Squares: ");
        for(CaptureSquare cs : capturedSquares){
            System.out.println(cs);
        }

        StringBuilder compactedEq = new StringBuilder();
        for(CaptureSquare cs : capturedSquares){
            compactedEq.append(varOfCaptureSquare(cs, k_matrix)).append(" + ");
        }
        compactedEq.delete(compactedEq.length() -2, compactedEq.length()-1);

        System.out.println("\n\nF = " + compactedEq);
        printMatrix(k_matrix, cellIndex);
    }

    // cell (r,c) → bit index
    static int cellIndex(int r, int c, int cols){ return r * cols + c; }

    // group identity = one long — exact, no collisions, order-independent
    static long groupMask(int startR, int startC, int h, int w, Box[][] m){
        int rows = m.length, cols = m[0].length;
        long mask = 0;
        for(int i=0;i<h;i++)
            for(int j=0;j<w;j++)
                mask |= (1L << cellIndex((startR+i)%rows, (startC+j)%cols, cols));
        return mask;
    }


    static String varOfCaptureSquare(CaptureSquare cs, Box[][]matrix){
       Box[][]captured = cs.getBoxes(matrix);
       StringBuilder varsConcat = new StringBuilder();
       HashMap<Character, List<Integer>>charIndexes = new HashMap<>();
       HashSet<Character> notAgain = new HashSet<>();
       System.out.println("Handle Boxes w Var:: ");
       for(Box[] boxes : captured){
           for(Box box : boxes){
               System.out.print(STR."\{box.varCombo} ");
               varsConcat.append(box.varCombo);
           }
       }

       return cleanVarConcat(varsConcat.toString());
    }

    /*static HashMap<Character, List<Integer>> handleCancel(Box box, String varConcat, HashMap<Character, List<Integer>>charIndexes, HashSet<Character>notAgain){
        if(varConcat.isEmpty()){
            return null;
        }
        String boxVar = box.varCombo;
        HashMap<Character, List<Integer>>charIndexCopy = new HashMap<>();
        for (var entry : charIndexes.entrySet()) {
            // Make a NEW mutable list for EVERY entry
            List<Integer> original = entry.getValue();
            List<Integer> mutableCopy = new ArrayList<>(original);  // ← critical line
            charIndexCopy.put(entry.getKey(), mutableCopy);
        }

        for(int i = 0; i<boxVar.length(); i++){
            for(int j = 0; j<varConcat.length(); j++){
                char curChar = varConcat.charAt(j);
                char upper = varConcat.toUpperCase().charAt(j);
                char lower = varConcat.toLowerCase().charAt(j);
                if(notAgain.contains(upper)){
                    System.out.println("Var " + curChar + " Already Canceled!");
                    continue;
                }

                char pair = curChar < 'a' ?  lower : upper;

                if(!charIndexCopy.containsKey(curChar)){
                    charIndexCopy.put(curChar, new ArrayList<>(List.of(j)));
                }else{
                    if(charIndexCopy.containsKey(pair)){
                        notAgain.add(upper);
                        System.out.println("Found Pair for Var: " + curChar);
                        varConcat = cleanVarConcat(varConcat);
                        charIndexCopy.remove(upper);
                        continue;
                    }
                    System.out.println(STR."Found the same Var: \{curChar}. Adding to list with indexes: \{String.join(", ", charIndexCopy.get(curChar).stream().map(index -> Integer.toString(index)).collect(Collectors.toCollection(ArrayList::new)))}");
                    charIndexCopy.get(curChar).add(j);
                }
            }
        }
        return charIndexCopy;
    }*/


    /*static String removeVarFromString(String varConcat, List<Integer>indexes){
        StringBuilder sb = new StringBuilder();
        ArrayList<Character> charArr = new ArrayList<>();
        for(char var : varConcat.toCharArray()){
            charArr.add(var);
        }
        int index =0;
        System.out.println("REMOVING vars from the concat: " + varConcat);
        for(char var : charArr){
            if(indexes.contains(index++)){
                System.out.print("removed " + var + ", ");
                continue;
            }
            sb.append(var);
        }
        return sb.toString();
    }*/

    static String cleanVarConcat(String varConcat){
        HashSet<Character> set = new HashSet<>();
        HashSet<Character> toRemove = new HashSet<>();
        HashSet<Character> uniqueChar = new HashSet<>();
        int letterIndex=  0;
        for(char letter : varConcat.toCharArray()){
            if(letter < 'a'){
                //upperCase
                if(set.contains(varConcat.toLowerCase().charAt(letterIndex))){
                    toRemove.add(letter);
                    continue;
                }
            }else{
                //lowerCase
                if(set.contains(varConcat.toUpperCase().charAt(letterIndex))){
                    toRemove.add(letter);
                    continue;
                }
            }
            set.add(letter);
        }
        StringBuilder sb = new StringBuilder();
        for(char letter : varConcat.toCharArray()){
            if(!toRemove.contains(letter) && !uniqueChar.contains(letter)){
                sb.append(letter);
                uniqueChar.add(letter);
            }
        }
        return sb.toString();
    }

    static boolean isNotIsolated(Box box, Box[][]matrix){
        int [][]adjacent =searchAdjacent(box.cords, matrix);
        for(int[] cord : adjacent){
            if(!getBoxOfCord(cord, matrix).filled){
                return true;
            }
        }
        return false;
    }

    //top left corner box
    static boolean isTLCornerBox(Box box, Box[][]matrix){
        int[][] result = searchAdjacent(box.cords, matrix);
//        System.out.println("Boxed Check on " + box.getCords());
//        System.out.println("TOP: " + result[TOP][ROW] +  "," + result[TOP][COL]);
        return (!(getBoxOfCord(result[TOP], matrix).filled) || !(getBoxOfCord(result[TOP], matrix).captured))&&
                (!(getBoxOfCord(result[LEFT], matrix).filled) || !(getBoxOfCord(result[LEFT], matrix).captured));
    }

    static Box getBoxOfCord(int[]cord, Box[][]matrix){
        return matrix[cord[ROW]][cord[COL]];
    }


    static int[] findBiggestBox(int[]startCord, Box[][]matrix, ArrayList<CaptureSquare>cs){
        double maxSizeX = Math.log(matrix[0].length) / Math.log(2);
        double maxSizeY = Math.log(matrix.length) / Math.log(2);

        ArrayList<int[]>boxSizes = new ArrayList<>();
//        System.out.println("Box Capture Possible Sizes: ");
//        int count = 0;
        for(int i = 0; i<=maxSizeY; i++){
            for(int j = 0; j<=maxSizeX; j++){
                boxSizes.add(new int[]{(int)Math.pow(2, i), (int)Math.pow(2, j)});
//                System.out.println(STR."(W x H) = (\{boxSizes.get(count)[COL]} x \{boxSizes.get(count++)[ROW]})");
            }
        }
        HashSet<Set<Integer>>uniqueIndexHash = new HashSet<>();
        if(!cs.isEmpty()){
            ArrayList<CaptureSquare> forCord = cs.stream().filter(c -> c.start == startCord).collect(Collectors.toCollection(ArrayList::new));
            ArrayList<Integer> shapeMap = forCord.stream().map(c -> Arrays.hashCode(c.shape)).collect(Collectors.toCollection(ArrayList::new));
            boxSizes = boxSizes.stream().filter(bs -> !shapeMap.contains(Arrays.hashCode(bs))).collect(Collectors.toCollection(ArrayList::new));
            forCord.forEach(fc ->{
                Set<Integer> group =arrToSet(fc.indexesHash);
                uniqueIndexHash.add(group);
            });
        }


        int[]maxSize = new int[2];
        for (int[] boxSize : boxSizes) {
            ibafResult result = isBoxAllFiled(startCord, matrix, boxSize);
            if (result.fullyFilled) {
                int size = sizeBox(boxSize);
                System.out.println(STR."Found Full box with Size: \{size}(\{boxSize[COL]} x \{boxSize[ROW]}) at \{matrix[startCord[ROW]][startCord[COL]].getCords()}");
                if (size > sizeBox(maxSize) && !uniqueIndexHash.contains(arrToSet(result.indexHashes))) {
                    maxSize = boxSize;
                }
            }
        }

        return maxSize;
    }

    static Set<Integer> arrToSet(int[] arr) {
        return Arrays.stream(arr)
                .boxed()              // int → Integer
                .collect(Collectors.toSet());
    }


    static int sizeBox(int[]widthHeight) {return widthHeight[ROW] * widthHeight[COL];}

    record ibafResult (boolean fullyFilled, int[]indexHashes){}
    //end must be to the bottom right of start
    static ibafResult isBoxAllFiled(int[]start, Box[][]matrix, int[]boxSize){
//        int width = 0;
//        int curCordsX = start[COL];
//        do{
//            width++;
//            curCordsX = next(new int[]{start[ROW], curCordsX}, matrix, RIGHT)[COL];
//        }while(curCordsX != end[COL] && width < matrix[0].length);
//
//        int height = 0;
//        int curCordsY = start[ROW];
//        do{
//            height++;
//            curCordsY = next(new int[]{curCordsY, start[COL]}, matrix, BOTTOM)[ROW];
//        }while(curCordsY != end[ROW] && height < matrix.length);
//
        int curCordsX = start[COL];
        int curCordsY = start[ROW];


        int height = boxSize[ROW];
        int width = boxSize[COL];
        int[]indexHash = new int[height * width];
        int count = 0;
        boolean foundUncaptured = false;
        for(int i = 0; i<height; i++){
            for(int j = 0; j<width; j++){
                int dir = i % 2 == 0 ? RIGHT : LEFT;
                if(!matrix[curCordsY][curCordsX].filled){
                    return new ibafResult(false, indexHash);
                }
                if(!matrix[curCordsY][curCordsX].captured){
                    foundUncaptured = true;
                }
                indexHash[count++] = Arrays.hashCode(new int[]{curCordsY, curCordsX});
                if(j != width-1){
                    int[]nextCords = next(new int[]{curCordsY, curCordsX} , matrix, dir);
                    curCordsX = nextCords[COL];
                    curCordsY = nextCords[ROW];
                }

            }
            if(i != height-1){
                int[]nextCords = next(new int[]{curCordsY, curCordsX}, matrix, BOTTOM);
                curCordsX = nextCords[COL];
                curCordsY = nextCords[ROW];
            }
        }

        return new ibafResult(foundUncaptured, indexHash);
    }

    static int[][] searchAdjacent(int[] cord, Box[][]matrix){
        int[][]result = new int[4][2];
        for(int i = 0; i<4; i++){
            result[i] = next(cord, matrix, i);
        }
        return result;
    }

    static int[] next(int[]cord, Box[][]matrix, int dir){
        return switch (dir) {
            case TOP -> new int[]{
                    cord[ROW] - 1 < 0 ? matrix.length - (1 - cord[ROW]) - 1 : cord[ROW] - 1,
                    cord[COL]
            };
            case BOTTOM -> new int[]{
                    cord[ROW] + 1 == matrix.length ? 0 : cord[ROW] + 1,
                    cord[COL]
            };
            case RIGHT -> new int[]{
                    cord[ROW],
                    cord[COL] + 1 == matrix[0].length ? 0 : cord[COL] + 1
            };
            case LEFT -> new int[]{
                    cord[ROW],
                    cord[COL] - 1 < 0 ? matrix[0].length - 1 : cord[COL] - 1
            };
            default -> new int[2];
        };
    }

    static void printMatrix(Box[][]matrix, String [][]cellIndex){
        int rowVarLength = cellIndex[ROW][0].length() + 1; //plus the space
        printSpace(rowVarLength);
        for(int i = 0; i<cellIndex[COL].length; i++){
            System.out.print(STR."  \{cellIndex[COL][i]}  ");
        }
        System.out.println();
        for(int i = 0; i<matrix.length; i++){
            printSpace(rowVarLength);
            printLine(matrix[0].length, 2);
            System.out.print(STR."\{cellIndex[ROW][i]} ");
            for(int j = 0; j<matrix[i].length; j++){
                String printing = " ";
                if(matrix[i][j].captured){
                    printing = "X";
                }else if(matrix[i][j].filled){
                    printing = "O";
                }
                System.out.print(STR."|  \{printing}  |");
            }
            System.out.println();
        }
        printSpace(rowVarLength);
        printLine(matrix[0].length, 2);
    }
    static void printSpace(int len){
        for(int i= 0; i<len; i++){
            System.out.print(" ");
        }
    }

    static void printLine(int len, int space){
        int rep = (3 + (2*space)) * len;
        for(int i= 0; i<rep; i++){
            System.out.print("=");
        }
        System.out.println();
    }

    static boolean isPrime(char letter){
        return ((int)letter) > 90;
    }

    static int[] getMatrixSize(int varAmount){
        int row = varAmount / 2  * 2;
        int col = row + 2 * (varAmount % 2);
        return new int []{row, col};
    }
}
