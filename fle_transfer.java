import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

class fle_transfer {
    // Sender side
    static boolean sendFile(String filePath, String serverAddress) {
        try (Socket socket = new Socket(serverAddress, 12345);
             FileInputStream fileInputStream = new FileInputStream(filePath);
             OutputStream outputStream = socket.getOutputStream();
             InputStream inputStream = socket.getInputStream()) {
         
            // Wait for receiver's acknowledgment
            if (!waitForReceiverAck(inputStream)) {
            	System.out.println("line 17");
                JOptionPane.showMessageDialog(null, "Receiver Rejected File");
                return false;
            }

            // Send file content
            byte[] buffer = new byte[4096];
            int bytesRead;
            System.out.println("line 24");
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            	System.out.println(bytesRead);
                outputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File sent successfully.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Receiver side
    static boolean receiveFile() {
        try (ServerSocket serverSocket = new ServerSocket(12345);
             Socket socket = serverSocket.accept();
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {

            // Receive filename
            String filename = receiveFilename(socket);
            System.out.println("Received filename: " + filename);

            // Prompt user to accept or reject the file
            int option = JOptionPane.showConfirmDialog(null, "Do you want to receive the file: " + filename, "File Transfer", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                // Send acknowledgment to sender
                sendReceiverAck(outputStream, true);
                String saveDirectory=welcome.getdire();

                // Receive file content
                File file = new File(saveDirectory + File.separator + filename);
                try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                    byte[] buffer = new byte[4096];
                    System.out.println("line 58");
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        System.out.println("Bytes read: " + bytesRead);
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }

                    System.out.println("File received successfully.");
                    
                }
                return true;
            } else {
                // Send acknowledgment to sender
                sendReceiverAck(outputStream, false);
                System.out.println("File transfer rejected by receiver.");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to send filename
    static boolean sendFilename( String ipadd,String filename) {
        try {
        	   filename = new File(filename).getName();
               try (Socket socket = new Socket(ipadd,12345)) {
				// Send filename length
				OutputStream outputStream = socket.getOutputStream();
				outputStream.write(filename.length());

				// Send filename
				outputStream.write(filename.getBytes());
				outputStream.flush();
			}

            System.out.println("Filename sent: " + filename);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to receive filename
    static String receiveFilename(Socket socket) {
        try {
            // Receive filename length
            InputStream inputStream = socket.getInputStream();
            int filenameLength = inputStream.read();

            // Receive filename bytes
            byte[] filenameBytes = new byte[filenameLength];
            int totalBytesRead = 0;
            while (totalBytesRead < filenameLength) {
                int bytesRead = inputStream.read(filenameBytes, totalBytesRead, filenameLength - totalBytesRead);
                if (bytesRead == -1) {
                    // End of stream reached prematurely
                    System.out.println("Failed to receive full filename.");
                    return null;
                }
                totalBytesRead += bytesRead;
            }
            // Convert filename bytes to string
            String filename = new String(filenameBytes);
            System.out.println("Received filename: " + filename);

            return filename;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to wait for receiver's acknowledgment
    static boolean waitForReceiverAck(InputStream inputStream) {
        try {
            byte[] acknowledgment = new byte[5];
            int bytesRead = inputStream.read(acknowledgment);
            String acknowledgmentStr = new String(acknowledgment, 0, bytesRead);
            System.out.println("line139");
            boolean abc=acknowledgmentStr.equals("ACCEPT");
            System.out.println(abc);
            return abc;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to send acknowledgment from receiver to sender
    static void sendReceiverAck(OutputStream outputStream, boolean accepted) {
        try {
            String acknowledgment = accepted ? "ACCEPT" : "REJECT";
            System.out.println(acknowledgment);
            outputStream.write(acknowledgment.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("line 152");
        }
    }
}