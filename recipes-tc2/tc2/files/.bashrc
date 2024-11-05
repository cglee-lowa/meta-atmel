if [ -z "$SSH_TTY" ]; then
    echo "serial login"
    python3 --version
else
    echo "SSH login"
fi