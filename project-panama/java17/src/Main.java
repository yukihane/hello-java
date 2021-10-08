import static com.example.greeter_h.*;
import static jdk.incubator.foreign.CLinker.*;

import jdk.incubator.foreign.*;
import java.awt.BorderLayout;
import java.io.Serial;
import java.nio.charset.StandardCharsets;
import javax.swing.*;

public class Main extends JFrame {

    @Serial
    private static final long serialVersionUID = 4648172894076113183L;

    public Main() {
        super("Rust GUI Frontend by Java Swing");
        setLayout(new BorderLayout());

        final JTextField nameField = new JTextField(20);

        final JTextField outputField = new JTextField(30);
        outputField.setEditable(false);

        final JButton greetButton = new JButton("greet");
        greetButton.addActionListener((e) -> {
            try (ResourceScope scope = ResourceScope.newConfinedScope()) {
                final SegmentAllocator allocator = SegmentAllocator.ofScope(scope);
                final MemorySegment name = toCString(nameField.getText(), scope);
                final long size = 256;
                final MemorySegment message = allocator.allocateArray(C_CHAR, size);

                greet(name, message, size);

                // Project PanamaのJDKには存在するが、通常のJDK17には無い
                // final String retval = toJavaString(message, StandardCharsets.UTF_8);
                final String retval = toJavaString(message);
                outputField.setText(retval);
            }
        });

        add(nameField, BorderLayout.WEST);
        add(greetButton, BorderLayout.EAST);
        add(outputField, BorderLayout.SOUTH);
        pack();
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            final Main app = new Main();
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.setVisible(true);
        });
    }
}
